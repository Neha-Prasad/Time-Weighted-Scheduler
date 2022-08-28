package TestDataGenerator;

import Utils.ICsvHeader;

import java.util.Objects;

public class TestData implements ICsvHeader {
    private String Name;
    private int ArrivalTime;
    private int ExecutionTimeInSeconds;
    private boolean IsExecuting;

    public boolean isExecuting() {
        return IsExecuting;
    }

    public void setExecuting(boolean executing) {
        IsExecuting = executing;
    }

    public void setExecutionTimeInSeconds(int executionTimeInSeconds) {
        ExecutionTimeInSeconds = executionTimeInSeconds;
    }

    public TestData(String name, int arrivalTime, int ExecutionTimeInSeconds) {
        this.Name = name;
        this.ExecutionTimeInSeconds = ExecutionTimeInSeconds;
        this.ArrivalTime = arrivalTime;
        this.IsExecuting = false;
    }

    public String getName() {
        return Name;
    }

    public int getArrivalTime() { return ArrivalTime; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestData testData = (TestData) o;
        return ArrivalTime == testData.ArrivalTime && ExecutionTimeInSeconds == testData.ExecutionTimeInSeconds && IsExecuting == testData.IsExecuting && Objects.equals(Name, testData.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, ArrivalTime, ExecutionTimeInSeconds, IsExecuting);
    }
}
