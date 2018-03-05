package studentCoursePlanner.state;

import studentCoursePlanner.utill.CategoryQ;

import java.util.List;
import java.util.Queue;

public class Mandatory implements State {

    private CoursePlannerState planner;
    private int lpCategory = 0;
    private int dsAlgo = 0;
    private int hardware =0 ;
    private int dataAnalytics=0;
    private CategoryQ Q = null;
    private int c1Min = (int)'A';
    private int c1Max = (int)'D';
    private int c2Min = (int)'E';
    private int c2Max = (int)'H';
    private int c3Min = (int)'I';
    private int c3Max = (int)'L';
    private int c4Min = (int)'M';
    private int c4Max = (int)'P';
    public void initialize(){
        lpCategory = 0;
        dsAlgo = 0;
        hardware = 0;
        dataAnalytics = 0;
    }
    public Mandatory(CoursePlannerState coursePlannerStateIn){
        this.planner = coursePlannerStateIn;
        Q = new CategoryQ();
    }
    public boolean isElective(Character course){
        for(Enum ele: Category.Elective.values()) {
            if (course == ele.name().charAt(0)) {
                return true;
            }
        }
        return false;
    }
    public void getAllCategoryStatus(){
        System.out.println( lpCategory +" "+dsAlgo +" "+ hardware +" "+ dataAnalytics);
        if(lpCategory >=2 && dsAlgo >=2 && hardware >=2 && dataAnalytics >=2){
            planner.setMandatoryStatus(true);
        }
    }
    public void stateCheck(){
        getAllCategoryStatus();
        if(!planner.isMandatoryStatus() && planner.isElectivesStatus()){
            planner.setState(planner.getGraduated());
            System.out.println("Graduated");
        }else{
            planner.setState(planner.getMandatory());
        }
    }
    public boolean validate(Character element, Enum[] course){
        for (Enum ele : course) {
//            ele.
            if(element == ele.name().charAt(0)){
                return true;
            }
        }
        return false;
    }
    public String  findCategory(Character element){
        if(validate(element, Category.LongProgramming.values())){
            return "LongProgramming";
        }else if(validate(element, Category.DsAlgo.values())){
            return "dsAlgo";
        }
        else if(validate(element, Category.Hardware.values())){
            return "hardware";
        }
        else if(validate(element, Category.DataAnalytics.values())){
            return "dataAnalytics";
        }
        return null;
    }
    public void categoryValidate(Character element){
        if(validate(element, Category.LongProgramming.values())){
            this.lpCategory += 1;
        }
        if(validate(element, Category.DsAlgo.values())){
            this.dsAlgo += 1;
        }
        if(validate(element, Category.Hardware.values())){
            this.hardware += 1;
        }
        if(validate(element, Category.DataAnalytics.values())){
            this.dataAnalytics += 1;
        }

    }
    public void updatePrerequisites(){
        initialize();
        for(Character element: planner.getCourse()) {
            categoryValidate(element);
            stateCheck();
        }
    }
    public void updateDetails(Character element){
        planner.setCourse(element);
        planner.getWaitList().remove(element);
    }
    public void dispatchQueue(){
        System.out.println("dispatch Queue");

        initialize();

        for(Character element: planner.getCourse()) {
            categoryValidate(element);
        }
        for(Character element: planner.getWaitList()) {
            if(element>= c1Min && element<c1Max){
                c1Min = (int) element;
                c1Max = (int) 'E';
                System.out.println("inserted here"+element);
               this.updateDetails(element);
            }
            if(element>= c2Min && element<c2Max){
                c2Min = (int) element;
                c2Max = (int) 'I';
                System.out.println("inserted here"+element);
                this.updateDetails(element);
            }
            if(element>= c3Min && element<c3Max){
                c3Min = (int) element;
                c3Max = (int) 'M';
                System.out.println("inserted here"+element);
                this.updateDetails(element);
            }
            if(element>= c4Min && element<c4Max){
                c4Min = (int) element;
                c4Max = (int) 'Q';
                System.out.println("inserted here"+element);
                this.updateDetails(element);
            }

//            else if(element>lp &&(element < dsa || dsAlgo >=1)){
//                this.updateDetails(element);
//            }else if(element >dsa && (element < hs || hardware >=1)){
//                this.updateDetails(element);
//            }else if(element > hs && (element < da || dataAnalytics >=1)){
//                this.updateDetails(element);
//            }
            planner.setState(planner.getMandatory());
            System.out.println(element);
//            categoryValidate(element);
//            stateCheck();
        }
    }
//    public void dispatchQueue(Character Currentcourse, String cat){
////        Q.setCategory("LongProgramming");
////        Q.setCourse('A');
////        planner.setWaitList(Q);
////        if(planner.getWaitList().poll() == null){
////            Q.setCategory(cat);
////            Q.setCourse(course);
////            planner.setWaitList(Q);
////        }
//        boolean found = false;
//
//        for(CategoryQ item : planner.getWaitList()){
//            System.out.println("items -=="+item.getCategory());
//            if(item.getCategory().equals(cat)){
//                if(Currentcourse < item.getCourse()){
//                    planner.setCourse(Currentcourse);
//                }else{
////                    planner.
//                }
//                found = true;
//            }
//        }
//        if(!found ){
//            System.out.println("enter false");
//            Q.setCategory(cat);
//            Q.setCourse(Currentcourse);
//            planner.setWaitList(Q);
//        }
//    }
    @Override
    public void assignCourse(Character course){
        if(isElective(course)){
            planner.setCourse(course);
            planner.setState(planner.getElective());
            return;
        }
        String cat = findCategory(course);
//        System.out.println(course);
//        System.out.println(cat);
        planner.getWaitList().add(course);
//        dispatchQueue(course,cat);
        dispatchQueue();
        planner.setState(planner.getMandatory());
//        this.updatePrerequisites();
        System.out.println("mandatory Course");
    }
}
