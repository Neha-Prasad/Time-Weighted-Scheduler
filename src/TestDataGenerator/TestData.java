package TestDataGenerator;

import Utils.ICsvHeader;

public class TestData implements ICsvHeader {
    private String Name;
    private int ExecutionTimeInSeconds;

    public TestData(String name, int ExecutionTimeInSeconds) {
        this.Name = name;
        this.ExecutionTimeInSeconds = ExecutionTimeInSeconds;
    }

    public String getName() {
        return Name;
    }

    public int getExecutionTime() {
        return ExecutionTimeInSeconds;
    }

    @Override
    public String toString() {
        return this.Name + "," + this.ExecutionTimeInSeconds;
    }

    @Override
    public String GetHeader() {
        return "Name,ExecutionTimeInSeconds";
    }
}
