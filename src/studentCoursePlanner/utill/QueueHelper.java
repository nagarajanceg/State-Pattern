package studentCoursePlanner.utill;

import studentCoursePlanner.state.CoursePlannerState;

public class QueueHelper {
    private int c1Min = (int)'A';
    private int c1Max = (int)'D';
    private int c2Min = (int)'E';
    private int c2Max = (int)'H';
    private int c3Min = (int)'I';
    private int c3Max = (int)'L';
    private int c4Min = (int)'M';
    private int c4Max = (int)'P';
    private CoursePlannerState planner = null;
    public void updateDetails(Character element){
        planner.setCourse(element);
        planner.getWaitList().remove(element);
    }
    public void dispatch(CoursePlannerState planner){
        this.planner = planner;
        System.out.println("dispatch Queue");
        for(Character element: planner.getWaitList()) {
            if(element>= c1Min && element<c1Max){
                c1Min = (int) element;
                c1Max = (int) 'E';
                this.updateDetails(element);
            }
            if(element>= c2Min && element<c2Max){
                c2Min = (int) element;
                c2Max = (int) 'I';
                this.updateDetails(element);
            }
            if(element>= c3Min && element<c3Max){
                c3Min = (int) element;
                c3Max = (int) 'M';
                this.updateDetails(element);
            }
            if(element>= c4Min && element<c4Max){
                c4Min = (int) element;
                c4Max = (int) 'Q';
                this.updateDetails(element);
            }
        }
    }

}
