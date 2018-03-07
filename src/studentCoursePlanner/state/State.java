package studentCoursePlanner.state;

public interface State {

    void assignCourse(Character ch);

    void verifyPrerequisiteState();
}
