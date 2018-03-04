package studentCoursePlanner.state;

public class Mandatory implements State {

    private CoursePlannerState planner;
    private int lpCategory;
    private int dsAlgo;
    private int hardware;
    private int dataAnalytics;

    public void initialize(){
        lpCategory = 0;
        dsAlgo = 0;
        hardware = 0;
        dataAnalytics = 0;
    }
    public Mandatory(CoursePlannerState coursePlannerStateIn){
        this.planner = coursePlannerStateIn;
    }
    public boolean isElective(String course){
        for(Enum ele: Category.Elective.values()) {
            if (course.equals(ele.name())) {
                return true;
            }
        }
        return false;
    }
    public void getAllCategoryStatus(){
        System.out.println( lpCategory +" "+dsAlgo +" "+ hardware +" "+ dataAnalytics);
        if(lpCategory >=2 && dsAlgo >=2 && hardware >=2 &&dataAnalytics >=2){
            planner.setMandatoryStatus(true);
        }
    }
    public void stateCheck(){
        getAllCategoryStatus();
        if(!planner.isMandatoryStatus() && planner.isElectivesStatus()){
            planner.setState(planner.getGraduated());
            System.out.println("graduated");
        }else{
            planner.setState(planner.getMandatory());
        }
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
        for(String element: planner.getCourse()) {
            categoryValidate(element);
            stateCheck();
        }
    }
    @Override
    public void assignCourse(String course){
        planner.setCourse(course);
        if(isElective(course)){
            planner.setState(planner.getElective());
            return;
        }
        planner.setState(planner.getMandatory());
        this.updatePrerequisites();
        System.out.println("mandatory Course");
    }
}
