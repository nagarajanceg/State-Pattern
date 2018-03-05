package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;

public class NotGraduated implements State {
    private CoursePlannerState planner;
    private int lpCategory;
    private int dsAlgo;
    private int hardware;
    private int dataAnalytics;
    private QueueHelper Queue = null;
    public void initialize(){
        lpCategory = 0;
        dsAlgo = 0;
        hardware = 0;
        dataAnalytics = 0;
    }
    public NotGraduated(CoursePlannerState coursePlannerStateIn) {
        this.planner = coursePlannerStateIn;
        this.Queue = planner.getQueue();
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
        planner.setWaitList(course);
        Queue.dispatch(planner);
//        planner.setCourse(course);
        planner.setState(planner.getMandatory());
    }

    @Override
    public void updatePrerequisites() {

    }

}
