package TestDataGenerator;

import java.time.Duration;

public class TestData {
    private String Name;
    private Duration ExecutionTime;

    public TestData(String name, Duration executionTime) {
        Name = name;
        ExecutionTime = executionTime;
    }

    public String getName() {
        return Name;
    }

    public Duration getExecutionTime() {
        return ExecutionTime;
    }
}
