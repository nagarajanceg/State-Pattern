package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;
import studentCoursePlanner.utill.StateHelper;

public class Mandatory implements CoursePlannerStateI {

    private CoursePlannerState planner;
    private int lpCategory = 0;
    private int dsAlgo = 0;
    private int hardware = 0;
    private int dataAnalytics = 0;
    private QueueHelper Queue = null;
    private StateHelper helper = null;

    private void initialize() {
        lpCategory = 0;
        dsAlgo = 0;
        hardware = 0;
        dataAnalytics = 0;
    }

    /**
     *
     * @param coursePlannerStateIn
     */
    public Mandatory(CoursePlannerState coursePlannerStateIn) {
        this.planner = coursePlannerStateIn;
        this.Queue = coursePlannerStateIn.getQueue();
        this.helper = coursePlannerStateIn.getHelper();
    }

    /**
     * Check the category course assigned values and set status
     */
    private void getAllCategoryStatus() {
        if (lpCategory >= 2 && dsAlgo >= 2 && hardware >= 2 && dataAnalytics >= 2) {
            planner.setMandatoryStatus(true);
        }
    }

    /**
     * To verify and make sure status moved graduated
     */
    public void verifyPrerequisiteState() {
        Queue.emptyQueue();
        if (!(planner.isMandatoryStatus() && planner.isElectivesStatus())) {
            planner.setCoursePlannerStateI(planner.getNot_graduated());
        }
    }
    /**
     * Check the category status and made transition to the state
     */
    private void stateCheck() {
        if (planner.isMandatoryStatus() && planner.isElectivesStatus()) {
            planner.setCoursePlannerStateI(planner.getGraduated());
        } else {
            planner.setCoursePlannerStateI(planner.getMandatory());
        }
    }

    /**
     *
     * @param element- course name
     * Add up the corresponding categories
     */
    private void categoryValidate(Character element) {
        if (helper.validate(element, Category.LongProgramming.values())) {
            this.lpCategory += 1;
        }
        if (helper.validate(element, Category.DsAlgo.values())) {
            this.dsAlgo += 1;
        }
        if (helper.validate(element, Category.Hardware.values())) {
            this.hardware += 1;
        }
        if (helper.validate(element, Category.DataAnalytics.values())) {
            this.dataAnalytics += 1;
        }
    }
    /**
     * update the category counts and state
     */
    public void updatePrerequisites() {
        initialize();
        for (Character element : planner.getCourse()) {
            categoryValidate(element);
        }
        getAllCategoryStatus();
        stateCheck();
    }

    @Override
    public void assignCourse(Character course) {
        if (helper.isElective(course)) {
            planner.setCourse(course);
            planner.setCoursePlannerStateI(planner.getElective());
            return;
        }
        planner.getWaitList().add(course);
        Queue.dispatch();
        planner.setCoursePlannerStateI(planner.getMandatory());
    }

    @Override
    public String toString() {
        return "Mandatory{" +
                "planner=" + planner +
                '}';
    }
}
