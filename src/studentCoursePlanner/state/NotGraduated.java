package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;

public class NotGraduated implements State {

    private CoursePlannerState planner;
    private QueueHelper Queue = null;

    public NotGraduated(CoursePlannerState coursePlannerStateIn) {
        this.planner = coursePlannerStateIn;
        this.Queue = planner.getQueue();
    }

    @Override
    public void assignCourse(Character course) {
        for (Enum ele : Category.Elective.values()) {
            if (course == ele.name().charAt(0)) {
                planner.setCourse(course);
                planner.setState(planner.getElective());
                return;
            }
        }
        planner.setWaitList(course);
        Queue.dispatch();
        planner.setState(planner.getMandatory());
    }

    @Override
    public void updatePrerequisites() {

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
