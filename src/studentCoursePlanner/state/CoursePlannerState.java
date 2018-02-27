package studentCoursePlanner.state;

import java.util.ArrayList;
import java.util.List;

public class CoursePlannerState {
    State inProgress;
    State graduated;
    State notGraduated;
    List<String> course = null;
    State state;
    private boolean longProg;
    private boolean dataStructure;
    private boolean hardwareSeq;
    private boolean dataAnalytics;
    private boolean electives;
    public CoursePlannerState(){
        course = new ArrayList<>();
        initializeCategory();
        inProgress = new InProgress(this);
        graduated = new Graduated(this);
        state = inProgress;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    private void initializeCategory() {
        this.longProg = false;
        this.dataStructure = false;
        this.hardwareSeq = false;
        this.dataAnalytics = false;
        this.electives = false;
    }

    public boolean isLongProg() {
        return longProg;
    }

    public void setLongProg(boolean longProg) {
        this.longProg = longProg;
    }

    public void assign(String course){
//        System.out.println("course :"+ course);
        state.assignCourse(course);
    }

    public State getInProgress() {
        return inProgress;
    }

    public void setInProgress(State inProgress) {
        this.inProgress = inProgress;
    }

    public boolean isDataStructure() {
        return dataStructure;
    }

    public void setDataStructure(boolean dataStructure) {
        this.dataStructure = dataStructure;
    }

    public State getGraduated() {
        return graduated;
    }

    public void setGraduated(State graduated) {
        this.graduated = graduated;
    }

    public State getNotGraduated() {
        return notGraduated;
    }

    public void setNotGraduated(State notGraduated) {
        this.notGraduated = notGraduated;
    }

    public void setState(State stateIn) {
        this.state = stateIn;
    }
}
