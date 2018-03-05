package studentCoursePlanner.state;

public class Graduated implements State {

    private CoursePlannerState planner;

    public Graduated(CoursePlannerState coursePlannerState) {
        this.planner = coursePlannerState;
    }

    @Override
    public void assignCourse(Character str) {

    }

    @Override
    public void updatePrerequisites() {

    }

    @Override
    public void verifyPrerequisiteState() {
    }

    @Override
    public String toString() {
        return "Graduated{ State" + '}';
    }
}
