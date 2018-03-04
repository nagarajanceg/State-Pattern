package studentCoursePlanner.state;

import java.util.ArrayList;
import java.util.List;

public class CoursePlannerState {
    State noCourse;
    State elective;
    State mandatory;
    State graduated;
    State not_graduated;
    List<String> course = null;
    State state;
    private boolean mandatoryStatus = false;
    private boolean electivesStatus = false;
    public CoursePlannerState(){
        course = new ArrayList<>();
        initializeCategory();
        noCourse = new NoCourse(this);
        elective = new Elective(this);
        mandatory = new Mandatory(this);
        graduated = new Graduated(this);
        not_graduated = new NonGraduated(this);
        state = noCourse;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course.add(course);
    }

    private void initializeCategory() {
        this.mandatoryStatus = false;
        this.electivesStatus = false;
    }

    public void assign(String course){
        state.assignCourse(course);
    }

    public State getMandatory(){
        return mandatory;
    }

    public State getElective() {
        return elective;
    }

    public State getGraduated() {
        return graduated;
    }

    public void setGraduated(State graduated) {
        this.graduated = graduated;
    }

    public void setElective(State elective) {
        this.elective = elective;
    }

    public void setMandatory(State mandatory) {
        this.mandatory = mandatory;
    }

    public State getNot_graduated() {
        return not_graduated;
    }

    public void setNot_graduated(State not_graduated) {
        this.not_graduated = not_graduated;
    }

    public boolean isMandatoryStatus() {
        return mandatoryStatus;
    }

    public void setMandatoryStatus(boolean mandatoryStatus) {
        this.mandatoryStatus = mandatoryStatus;
    }

    public boolean isElectivesStatus() {
        return electivesStatus;
    }

    public void setElectivesStatus(boolean electivesStatus) {
        this.electivesStatus = electivesStatus;
    }

    public void setState(State stateIn) {
        this.state = stateIn;
    }
}
