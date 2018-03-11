package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;
import studentCoursePlanner.utill.StateHelper;

public class NotGraduated implements CoursePlannerStateI {

    private CoursePlannerState planner;
    private QueueHelper Queue = null;
    private StateHelper helper = null;

    public NotGraduated(CoursePlannerState coursePlannerStateIn) {
        this.planner = coursePlannerStateIn;
        this.Queue = coursePlannerStateIn.getQueue();
        this.helper = coursePlannerStateIn.getHelper();
    }

    @Override
    public void assignCourse(Character course) {
        if (helper.isElective(course)) {
            planner.setCourse(course);
            planner.setCoursePlannerStateI(planner.getElective());
            return;
        }
        planner.setWaitList(course);
        Queue.dispatch();
        planner.setCoursePlannerStateI(planner.getMandatory());
    }

    @Override
    public void verifyPrerequisiteState() {
        if (!(planner.isMandatoryStatus() && planner.isElectivesStatus())) {
            planner.setCoursePlannerStateI(planner.getNot_graduated());
        }
    }

    @Override
    public void updatePrerequisites() {

    }

    @Override
    public String toString() {
        return "NotGraduated{" +
                "planner=" + this.getClass().getSimpleName() +
                '}';
    }
}
