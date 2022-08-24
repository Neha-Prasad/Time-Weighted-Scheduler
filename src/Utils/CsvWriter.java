package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class CsvWriter<T extends ICsvHeader> implements ICsvWriter<T> {

    private String fileName;

    public CsvWriter(String fileName)
    {
        this.fileName = fileName;
    }

    @Override
    public boolean Write(List<T> listOfData) {
        try {
            File file = new File(this.fileName);
            if (file.exists()) {
                file.delete();
            }
            boolean headerWritten = false;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fileName), "UTF-8"));
            for (T t : listOfData) {
                if (!headerWritten) {
                    bw.write(t.GetHeader());
                    bw.newLine();
                    headerWritten = true;
                }
                bw.write(t.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
