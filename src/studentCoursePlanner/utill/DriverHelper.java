package studentCoursePlanner.utill;

import studentCoursePlanner.state.CoursePlannerState;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class DriverHelper {
    private FileProcessor fp = null;
    List<Character> courseList = null;
    private String bNumber;
    public DriverHelper(){
        fp = new FileProcessor();
        courseList = new ArrayList<Character>();
    }
    public void parseInput(String str, CoursePlannerState coursePlanner){
        bNumber = str.split(":")[0];
        String[] course = str.split(":")[1].trim().split(" ");
        System.out.println("bNumber == "+ bNumber);

        for(String ch : course){
            coursePlanner.assign(ch);
        }
        System.out.println();
    }
    public void inputFileProcessor(String name, CoursePlannerState coursePlanner){
        BufferedReader reader;
        try{
            String line;
            reader = fp.readerDesc(name);
            while((line = fp.readLine(reader))!= null) {

                System.out.println(line);
                parseInput(line, coursePlanner);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("file reader");
            System.exit(0);
        }finally {

        }
    }

    @Override
    public String toString() {
        return "DriverHelper{}";
    }
}
