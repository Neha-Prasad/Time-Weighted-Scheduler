package TestRunner;

import Utils.ICsvHeader;

public class TestResult implements ICsvHeader {
    private String algorithmName;
    private long runTimeInSeconds;

    public TestResult(String algorithmName, long runTimeinMins) {
        this.algorithmName = algorithmName;
        this.runTimeInSeconds = runTimeinMins;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public long getRunTimeInSeconds() {
        return runTimeInSeconds;
    }

    @Override
    public String GetHeader() {
        return "AlgorithmName,RunTimeInSeconds";
    }

    @Override
    public String toString() {
        return algorithmName + "," + runTimeInSeconds;
    }
}
