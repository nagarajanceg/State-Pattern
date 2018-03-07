package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;
import studentCoursePlanner.utill.StateHelper;

public class NotGraduated implements State {

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
            planner.setState(planner.getElective());
            return;
        }
        planner.setWaitList(course);
        Queue.dispatch();
        planner.setState(planner.getMandatory());
    }

    @Override
    public void verifyPrerequisiteState() {
        if (!(planner.isMandatoryStatus() && planner.isElectivesStatus())) {
            planner.setState(planner.getNot_graduated());
        }
    }

    @Override
    public String toString() {
        return "NotGraduated{" +
                "planner=" + this.getClass().getSimpleName() +
                '}';
    }
}
