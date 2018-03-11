package studentCoursePlanner.state;

import studentCoursePlanner.utill.QueueHelper;
import studentCoursePlanner.utill.StateHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CoursePlannerState {

    private CoursePlannerStateI elective;
    private CoursePlannerStateI mandatory;
    private CoursePlannerStateI graduated;
    private CoursePlannerStateI not_graduated;
    private CoursePlannerStateI coursePlannerStateI;
    private boolean mandatoryStatus;
    private boolean electivesStatus;
    private StateHelper helper = null;

    private List<Character> course = null;
    private Queue<Character> waitList = null;
    private QueueHelper Queue = null;

    public CoursePlannerState() {
        course = new ArrayList<>();
        waitList = new LinkedList<>();
        Queue = new QueueHelper(this);
        this.mandatoryStatus = false;
        this.electivesStatus = false;
        helper = new StateHelper();
        not_graduated = new NotGraduated(this);
        elective = new Elective(this);
        mandatory = new Mandatory(this);
        graduated = new Graduated(this);

        this.setCoursePlannerStateI(not_graduated);
    }

    public List<Character> getCourse() {
        return course;
    }

    public void setCourse(Character course) {
        this.course.add(course);
    }

    public void assign(Character course) {
        coursePlannerStateI.assignCourse(course);
    }

    public void verifyState() {
        coursePlannerStateI.verifyPrerequisiteState();
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

    public CoursePlannerStateI getMandatory() {
        return mandatory;
    }

    public CoursePlannerStateI getElective() {
        return elective;
    }

    public CoursePlannerStateI getGraduated() {
        return graduated;
    }

    public StateHelper getHelper() {
        return helper;
    }

    public void setGraduated(CoursePlannerStateI graduated) {
        this.graduated = graduated;
    }

    public void setElective(CoursePlannerStateI elective) {
        this.elective = elective;
    }

    public void setMandatory(CoursePlannerStateI mandatory) {
        this.mandatory = mandatory;
    }

    public CoursePlannerStateI getNot_graduated() {
        return not_graduated;
    }

    public void setNot_graduated(CoursePlannerStateI not_graduated) {
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

    public void setCoursePlannerStateI(CoursePlannerStateI coursePlannerStateIIn) {
        this.coursePlannerStateI = coursePlannerStateIIn;
    }

    public CoursePlannerStateI getCoursePlannerStateI() {
        return coursePlannerStateI;
    }

    public String getStateName() {
        return getCoursePlannerStateI().getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "CoursePlannerState{" +
                "coursePlannerStateI=" + coursePlannerStateI.getClass().getSimpleName() +
                ", course=" + course +
                '}';
    }
}
