package studentCoursePlanner.utill;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

    private BufferedWriter writer = null;
    private FileOutputStream fp = null;

    public Results(String name) {
        this.initialize(name);
    }

    private void initialize(String fileName) {
        try {
            fp = new FileOutputStream(fileName);
            writer = new BufferedWriter(new OutputStreamWriter(fp));
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public void writeInFile(String s) {
        try {
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Writing result file");
            System.exit(0);
        }finally {

        }
    }
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
    @Override
    public void writeToDisplay(String content) {
        System.out.println(content);
    }

    @Override
    public String toString() {
        return "Results{" + '}';
    }
}
