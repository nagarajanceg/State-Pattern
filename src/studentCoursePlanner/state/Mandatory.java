package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;

public class Mandatory implements State {

    private CoursePlannerState planner;
    private int lpCategory = 0;
    private int dsAlgo = 0;
    private int hardware =0 ;
    private int dataAnalytics=0;
    private QueueHelper Queue = null;
    public void initialize(){
        lpCategory = 0;
        dsAlgo = 0;
        hardware = 0;
        dataAnalytics = 0;
    }
    public Mandatory(CoursePlannerState coursePlannerStateIn){
        this.planner = coursePlannerStateIn;
        this.Queue = coursePlannerStateIn.getQueue();
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
        if(planner.isMandatoryStatus() && planner.isElectivesStatus()){
            planner.setState(planner.getGraduated());
            System.out.println("Graduated");
        }else{
            planner.setState(planner.getMandatory());
        }
    }
    public boolean validate(Character element, Enum[] course){
        for (Enum ele : course) {
            if(element == ele.name().charAt(0)){
                return true;
            }
        }
        return false;
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

    @Override
    public void assignCourse(Character course){
        if(isElective(course)){
            planner.setCourse(course);
            planner.setState(planner.getElective());
            return;
        }
        planner.getWaitList().add(course);
        Queue.dispatch(planner);
        planner.setState(planner.getMandatory());
        this.updatePrerequisites();
        System.out.println("mandatory Course");
    }
}
