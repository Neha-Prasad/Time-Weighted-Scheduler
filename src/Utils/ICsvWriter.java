package Utils;

import java.util.List;

public interface ICsvWriter<T> {
    public boolean Write(List<T> data);
}