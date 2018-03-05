package studentCoursePlanner.state;

public class NotGraduated implements State {
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
    public NotGraduated(CoursePlannerState coursePlannerStateIn) {
        this.planner = coursePlannerStateIn;
    }

    @Override
    public void assignCourse(Character course) {
        for(Enum ele: Category.Elective.values()){
            if(course == ele.name().charAt(0)){
                planner.setCourse(course);
                System.out.println("Not graduated course");
                planner.setState(planner.getElective());
                return;
            }
        }
//        planner.setWaitList(course);
//        dispatchQueue();
        planner.setCourse(course);
        planner.setState(planner.getMandatory());
    }

}
