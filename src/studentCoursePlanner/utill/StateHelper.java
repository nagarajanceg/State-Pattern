package studentCoursePlanner.utill;

import studentCoursePlanner.state.Category;

public class StateHelper {
    public StateHelper() {
    }

    /**
     * @param course
     * @return
     */
    public boolean isElective(Character course) {
        for (Enum ele : Category.Elective.values()) {
            if (course == ele.name().charAt(0)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param element
     * @param course
     * @return
     */
    public boolean validate(Character element, Enum[] course) {
        for (Enum ele : course) {
            if (element == ele.name().charAt(0)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "StateHelper{}";
    }
}
