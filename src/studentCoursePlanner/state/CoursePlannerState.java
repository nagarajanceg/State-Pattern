package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CoursePlannerState {
    private State elective;
    private State mandatory;
    private State graduated;
    private State not_graduated;
    private State state;
    private boolean mandatoryStatus;
    private boolean electivesStatus;

    private List<Character> course = null;
    private Queue<Character> waitList = null;
    private QueueHelper Queue = null;

    public CoursePlannerState(){
        course = new ArrayList<>();
        waitList = new LinkedList<>();
        Queue = new QueueHelper();
        initializeCategory();
        not_graduated = new NotGraduated(this);
        elective = new Elective(this);
        mandatory = new Mandatory(this);
        graduated = new Graduated(this);
        this.setState(not_graduated);
    }

    public List<Character> getCourse() {
        return course;
    }

    public void setCourse(Character course) {
        this.course.add(course);
    }

    private void initializeCategory() {
        this.mandatoryStatus = false;
        this.electivesStatus = false;
    }

    public void assign(Character course){
        state.assignCourse(course);
    }
    public void emptyQueue(){
        List<Character> temp  = new ArrayList<>();
        while( this.getWaitList().size() >0){
            temp.add( this.getWaitList().poll());
        }
        System.out.println("remaining elements");
        for(Character element: temp){
            System.out.println(element);
            this.assign(element);
        }
    }
    public Queue<Character> getWaitList() {
        return waitList;
    }

    public QueueHelper getQueue() {
        return Queue;
    }

    public void setWaitList(Character course) {
        this.waitList.add(course);
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

    public State getState() {
        return state;
    }
}
