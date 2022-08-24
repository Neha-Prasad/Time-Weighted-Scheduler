package Utils;

import java.util.List;

public interface ICsvWriter<T extends ICsvHeader> {
    public boolean Write(List<T> data);
}