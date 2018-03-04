package studentCoursePlanner.state;

public class NoCourse implements State {
    private CoursePlannerState planner;
    public NoCourse(CoursePlannerState coursePlannerStateIn) {
        this.planner = coursePlannerStateIn;
    }

    @Override
    public void assignCourse(String str) {
        planner.setCourse(str);
        for(Enum ele: Category.Elective.values()){
            if(str.equals(ele.name())){
                System.out.println("In No course");
                planner.setState(planner.getElective());
                return;
            }
        }
        planner.setState(planner.getMandatory());
    }

}
