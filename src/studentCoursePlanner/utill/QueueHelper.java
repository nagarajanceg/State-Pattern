package studentCoursePlanner.utill;

import studentCoursePlanner.state.CoursePlannerState;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class QueueHelper {

    private int c1Min = (int) 'A';
    private int c1Max = (int) 'D';
    private int c2Min = (int) 'E';
    private int c2Max = (int) 'H';
    private int c3Min = (int) 'I';
    private int c3Max = (int) 'L';
    private int c4Min = (int) 'M';
    private int c4Max = (int) 'P';
    private CoursePlannerState planner = null;

    public QueueHelper(CoursePlannerState plannerIn) {
        this.planner = plannerIn;
    }

    /**
     *
     * @param element
     */
    public void updateDetails(Character element) {
        planner.setCourse(element);
        planner.getWaitList().remove(element);
    }

    /**
     * Queue dispatch conditions
     */
    public void dispatch() {
        for (Character element : planner.getWaitList()) {
            if (element >= c1Min && element < c1Max) {
                c1Min = (int) element;
                c1Max = (int) 'E';
                this.updateDetails(element);
                break;
            }
            if (element >= c2Min && element < c2Max) {
                c2Min = (int) element;
                c2Max = (int) 'I';
                this.updateDetails(element);
                break;
            }
            if (element >= c3Min && element < c3Max) {
                c3Min = (int) element;
                c3Max = (int) 'M';
                this.updateDetails(element);
                break;
            }
            if (element >= c4Min && element < c4Max) {
                c4Min = (int) element;
                c4Max = (int) 'Q';
                this.updateDetails(element);
                break;
            }
        }
    }

    /**
     * Empty the Queue logic
     */
    public void emptyQueue() {
        List<Character> temp = new ArrayList<>();
        while (planner.getWaitList().size() > 0) {
            temp.add(planner.getWaitList().poll());
        }
        for (Character element : temp) {
            planner.assign(element);
        }
    }

    @Override
    public String toString() {
        return "QueueHelper{" + '}';
    }
}
