package TestDataGenerator;

import Utils.ICsvHeader;

public class TestData implements ICsvHeader {
    private String Name;
    private int ExecutionTimeInMins;

    public TestData(String name, int ExecutionTimeInMins) {
        this.Name = name;
        this.ExecutionTimeInMins = ExecutionTimeInMins;
    }

    public String getName() {
        return Name;
    }

    public int getExecutionTime() {
        return ExecutionTimeInMins;
    }

    @Override
    public String toString() {
        return this.Name + "," + this.ExecutionTimeInMins;
    }

    @Override
    public String GetHeader() {
        return "Name,ExecutionTimeInMins";
    }
}
