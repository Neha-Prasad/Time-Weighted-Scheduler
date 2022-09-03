package Utils;

import java.io.*;
import java.util.List;

public class CsvWriter<T> implements ICsvWriter<T> {

    private String fileName;

    public CsvWriter(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public boolean Write(List<T> listOfData) {
        try {
            FileWriter fw = new FileWriter(this.fileName, true);
            for (T t : listOfData) {
                fw.write(t.toString());
                fw.write(System.lineSeparator());
            }
            fw.flush();
            fw.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean WriteHeader(String header) {
        try {
            File file = new File(this.fileName);
            if (file.exists()) {
                file.delete();
            }
            FileWriter fw = new FileWriter(this.fileName, false);
            fw.write(header);
            fw.write(System.lineSeparator());
            fw.flush();
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
