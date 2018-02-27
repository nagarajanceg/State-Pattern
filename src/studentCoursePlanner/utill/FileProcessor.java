package studentCoursePlanner.utill;

import java.io.*;

public class FileProcessor {

    public BufferedReader readerDesc(String name){
        FileInputStream fp = null;
        BufferedReader reader = null;
        try{
            fp = new FileInputStream(name);
            reader = new BufferedReader(new InputStreamReader(fp));
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("File Not found");
            System.exit(0);
        }finally {

        }
        return reader;
    }

    public String readLine(BufferedReader reader){
        if(reader == null){
            return null;
        }
        String line = null;
        try{
            line = reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Read a line error");
            System.exit(0);
        }finally {

        }
        return line;
    }
}
