package studentCoursePlanner.driver;

import studentCoursePlanner.state.CoursePlannerState;
import studentCoursePlanner.utill.DriverHelper;
import studentCoursePlanner.utill.Results;

public class Driver {
    public static void main(String[] args){
        DriverHelper driver = new DriverHelper();
        CoursePlannerState coursePlanner = new CoursePlannerState();
        driver.inputFileProcessor("input.txt", coursePlanner);
        Results result = new Results("output.txt");
        driver.constructResults(coursePlanner, result);
    }
}
