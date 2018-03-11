package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;
import studentCoursePlanner.utill.StateHelper;

public class Elective implements CoursePlannerStateI {

    private CoursePlannerState planner;
    private int electiveCategory;
    private QueueHelper Queue = null;
    private StateHelper helper = null;
    /**
     *
     * @param coursePlannerStateIn
     */
    public Elective(CoursePlannerState coursePlannerStateIn) {
        this.planner = coursePlannerStateIn;
        this.Queue = coursePlannerStateIn.getQueue();
        this.helper = coursePlannerStateIn.getHelper();
    }

    private void initialize() {
        electiveCategory = 0;
    }

    @Override
    public void verifyPrerequisiteState() {
        Queue.emptyQueue();
        if (!(planner.isMandatoryStatus() && planner.isElectivesStatus())) {
            planner.setCoursePlannerStateI(planner.getNot_graduated());
        }
    }

    /**
     *
     * @param element - Course name
     */
    private void categoryValidate(Character element) {
        if (helper.validate(element, Category.Elective.values())) {
            this.electiveCategory += 1;
        }
        if (this.electiveCategory >= 2) {
            planner.setElectivesStatus(true);
        }
    }

    /**
     * Check the category status and made transition to the state
     */
    public void stateCheck() {
        if (planner.isMandatoryStatus() && planner.isElectivesStatus()) {
            planner.setCoursePlannerStateI(planner.getGraduated());
        } else {
            planner.setCoursePlannerStateI(planner.getElective());
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
        stateCheck();
    }

    /**
     *
     * @param course
     */
    @Override
    public void assignCourse(Character course) {
        if (helper.isElective(course)) {
            planner.setCoursePlannerStateI(planner.getElective());
            planner.setCourse(course);
//            this.updatePrerequisites();
            return;
        }
        planner.getWaitList().add(course);
        Queue.dispatch();
        planner.setCoursePlannerStateI(planner.getMandatory());
    }

    @Override
    public String toString() {
        return "Elective{}";
    }
}
