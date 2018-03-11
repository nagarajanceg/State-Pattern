package studentCoursePlanner.driver;

import studentCoursePlanner.state.CoursePlannerState;
import studentCoursePlanner.utill.DriverHelper;
import studentCoursePlanner.utill.Results;

public class Driver {
    public static void main(String[] args){
        if (args.length != 2 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
            System.exit(0);
        }
        DriverHelper driver = new DriverHelper();
        CoursePlannerState coursePlanner = new CoursePlannerState();
        driver.inputFileProcessor(args[0], coursePlanner);
        Results result = new Results(args[1]);
//        driver.inputFileProcessor("input.txt", coursePlanner);
//        Results result = new Results("output.txt");
        driver.constructResults(coursePlanner, result);
    }

    @Override
    public String toString() {
        return "Driver{}";
    }
}
