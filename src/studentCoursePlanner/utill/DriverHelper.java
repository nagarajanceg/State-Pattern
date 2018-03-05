package studentCoursePlanner.utill;

import studentCoursePlanner.state.CoursePlannerState;

import java.io.BufferedReader;

public class DriverHelper {
    private FileProcessor fp = null;
    private String bNumber;
    public DriverHelper(){
        fp = new FileProcessor();
    }
    public void parseInput(String str, CoursePlannerState coursePlanner){
        bNumber = str.split(":")[0];
        String[] course = str.split(":")[1].trim().split(" ");
        System.out.println("bNumber == "+ bNumber);
        for(String ch : course){
            coursePlanner.assign(ch.toCharArray()[0]);
        }
        System.out.println();
        coursePlanner.emptyQueue();

        for(Character element: coursePlanner.getCourse()) {
            System.out.print(element);
        }
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
