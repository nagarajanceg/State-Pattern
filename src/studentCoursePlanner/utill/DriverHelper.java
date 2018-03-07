package studentCoursePlanner.utill;

import studentCoursePlanner.state.CoursePlannerState;

import java.io.BufferedReader;
import java.util.List;

public class DriverHelper {

    private FileProcessor fp = null;
    private String bNumber;

    public DriverHelper() {
        fp = new FileProcessor();
    }

    /**
     *
     * @param plannerIn
     * @param resultsIn
     */
    public void constructResults(CoursePlannerState plannerIn, Results resultsIn) {
        String state = plannerIn.getStateName();
        List<Character> course = plannerIn.getCourse();
        StringBuffer result =new StringBuffer();
        result.append(bNumber+":");
        int sem = course.size();
        if (sem % 3 != 0) {
            sem  = sem/3;
            sem +=  1;
        }else {
            sem = sem/3;
        }

        for (Character ch : course) {
            result.append(ch + " ");
        }
        if (!state.equals("NotGraduated")){
            result.append("sem " + sem+": "+ state);
        }else{
            result.append(state);
        }
        resultsIn.writeInFile(result.toString());
        resultsIn.close();
    }

    /**
     *
     * @param str
     * @param coursePlanner
     */
    private void parseInput(String str, CoursePlannerState coursePlanner) {
        bNumber = str.split(":")[0];
        String[] course = str.split(":")[1].trim().split(" ");
        for (String ch : course) {
            if(coursePlanner.getStateName().equals("Graduated")){
                break;
            }
            System.out.println(ch);
            coursePlanner.assign(ch.toCharArray()[0]);
        }
        System.out.println();
        coursePlanner.verifyState();
        for (Character element : coursePlanner.getCourse()) {
            System.out.print(element);
        }
    }

    /**
     *
     * @param name
     * @param coursePlanner
     */
    public void inputFileProcessor(String name, CoursePlannerState coursePlanner) {
        BufferedReader reader;
        try {
            String line;
            reader = fp.readerDesc(name);
            while ((line = fp.readLine(reader)) != null) {
                System.out.println(line);
                parseInput(line, coursePlanner);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("file reader");
            System.exit(0);
        } finally {

        }
    }

    @Override
    public String toString() {
        return "DriverHelper{}";
    }
}
