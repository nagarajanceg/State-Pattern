package studentCoursePlanner.state;

public class NonGraduated implements State {
    private CoursePlannerState planner;
    public NonGraduated(CoursePlannerState coursePlannerState) {
        this.planner = coursePlannerState;
    }

    @Override
    public void assignCourse(String str) {

    }
}
