package studentCoursePlanner.state;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class InProgress implements State {
    private CoursePlannerState planner;
    private int lpCategory;
    private int DsAlgo;
    private int Hardware;
    private int DataAnalytics;
    private int Elective;
    public void initialize(){
        lpCategory = 0;
        DsAlgo = 0;
        Hardware = 0;
        DataAnalytics = 0;
        Elective = 0;
    }
    public InProgress(CoursePlannerState plannerIn) {
        this.planner = plannerIn;
    }
    public boolean validate(String element, Enum[] course){
        for (Enum ele : course) {
            if(element.equals(ele.name())){
                return true;
            }
        }
        return false;
    }
    public void categoryValidate(String element){
        if(validate(element, Category.LongProgramming.values())){
            this.lpCategory += 1;
            if( lpCategory >= 2){
                planner.setLongProg(true);
            }
        }
        if(validate(element, Category.DsAlgo.values())){
            this.DsAlgo += 1;
            if (this.DsAlgo >= 2){
                planner.setDataStructure(true);
            }
        }
    }
    public void stateCheck(){
        if(planner.isLongProg() && planner.isDataStructure()){
            planner.setState(planner.getGraduated());
            System.out.println("graduated");
        }
    }
    public void updatePrerequisites(){
        initialize();
        for(String element: planner.getCourse()) {
            categoryValidate(element);
//            if(this.lpCategory <2) {
//                System.out.println(Arrays.asList(Category.LongProgramming.values()).contains(element));
//            }
            stateCheck();
        }
    }

    @Override
    public void assignCourse(String course) {
        this.planner.course.add(course);
        this.updatePrerequisites();
    }
}
