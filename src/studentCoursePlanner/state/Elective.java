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

    public boolean isElective(String course){
        for(Enum ele: Category.Elective.values()) {
            if (course.equals(ele.name())) {
                return true;
            }
        }
        return false;
    }
    public boolean validate(String element, Enum[] course){
        for (Enum ele : course) {
            if(element.equals(ele.name())){
                return true;
            }
        }
        return false;
    }
    public void categoryValidate(String element) {
        if (validate(element, Category.Elective.values())) {
            this.electiveCategory += 1;
        }
        if (this.electiveCategory >=2){
            planner.setElectivesStatus(true);
        }
        this.stateCheck();
    }
    public void stateCheck(){
        if(!planner.isMandatoryStatus() && planner.isElectivesStatus()){
            planner.setState(planner.getGraduated());
            System.out.println("graduated");
        }else{
            planner.setState(planner.getElective());
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
    public void assignCourse(String course) {
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
