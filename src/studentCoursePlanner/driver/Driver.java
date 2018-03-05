package studentCoursePlanner.driver;

import studentCoursePlanner.state.CoursePlannerState;
import studentCoursePlanner.utill.DriverHelper;

public class Driver {
    public static void main(String[] args){
        DriverHelper drive = new DriverHelper();
        CoursePlannerState coursePlanner = new CoursePlannerState();
        drive.inputFileProcessor("input.txt", coursePlanner);
    }
}
