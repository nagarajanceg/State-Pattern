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

    public void constructResults(CoursePlannerState plannerIn, Results resultsIn) {
        System.out.println("Construct Results");
        String state = plannerIn.getStateName();
        List<Character> course = plannerIn.getCourse();
        String res = bNumber+":";
        int sem = course.size() / 3;

        if (sem % 3 != 0) {
            sem =  + 1;
        }

        for (Character ch : course) {
            res += ch + " ";
        }
        if (!state.equals("NotGraduated")){
            res += "sem " + sem+": "+ state;
        }else{
            res += state;
        }
        resultsIn.writeInFile(res);
        resultsIn.close();
    }

    public void parseInput(String str, CoursePlannerState coursePlanner) {
        bNumber = str.split(":")[0];
        String[] course = str.split(":")[1].trim().split(" ");
        for (String ch : course) {
            coursePlanner.assign(ch.toCharArray()[0]);
        }
        System.out.println();
        coursePlanner.verifyState();
        for (Character element : coursePlanner.getCourse()) {
            System.out.print(element);
        }
    }

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
