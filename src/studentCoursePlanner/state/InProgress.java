package studentCoursePlanner.state;

import java.util.ArrayList;
import java.util.List;

public class InProgress implements State {
    private CoursePlannerState planner;
    private int lpCategory;
//    public void initialize(){
//        lpCategory = 0;
//    }
    public InProgress(CoursePlannerState plannerIn) {
        this.planner = plannerIn;
//        initialize();
    }
    public void updatePrerequisites(){
        lpCategory = 0;
        for(String element: planner.getCourse()) {
            System.out.println("access Long programming enum "+ element);
            if(this.lpCategory <2) {
                for (Enum ele : Category.LongProgramming.values()) {
                    if (element.equals(ele.name())) {
                        this.lpCategory += 1;
                    }
                    System.out.println(ele + " = " + this.lpCategory);
                }
            }
            if (lpCategory >= 2) {
                planner.setLongProg(true);
                planner.setState(planner.getGraduated());
            }
        }

        if(planner.isLongProg()){
            System.out.println("graduated");
        }
    }

    @Override
    public void assignCourse(String course) {
        this.planner.course.add(course);
        this.updatePrerequisites();
    }
}
