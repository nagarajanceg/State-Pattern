package studentCoursePlanner.state;

public class Elective implements State {
    private CoursePlannerState planner;
    private int electiveCategory;
    public Elective(CoursePlannerState coursePlannerStateIn){
        this.planner = coursePlannerStateIn;
    }

    public void initialize(){
        electiveCategory = 0;
    }

    public boolean isElective(Character course){
        for(Enum ele: Category.Elective.values()) {
            if (course == ele.name().charAt(0)) {
                return true;
            }
        }
        return false;
    }
    public boolean validate(Character element, Enum[] course){
        for (Enum ele : course) {
            if(element == ele.name().charAt(0)){
                return true;
            }
        }
        return false;
    }
    public void categoryValidate(Character element) {
        if (validate(element, Category.Elective.values())) {
            this.electiveCategory += 1;
        }
        if (this.electiveCategory >=2){
            planner.setElectivesStatus(true);
        }
    }
    public void stateCheck(){
        if(!planner.isMandatoryStatus() && planner.isElectivesStatus()){
            planner.setState(planner.getGraduated());
            System.out.println("graduated in elective");
        }else{
            planner.setState(planner.getElective());
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
    public void assignCourse(Character course) {
//        planner.setWaitList(course);
        planner.setCourse(course);
        if (isElective(course)){
            planner.setState(planner.getElective());
            this.updatePrerequisites();
            return;
        }
        planner.setState(planner.getMandatory());
        System.out.println("In Elective course");
    }
}
