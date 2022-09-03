package TestRunner;

public class TestResult {
    private String algorithmName;
    private long runTimeInSeconds;
    private int numberOfHilsUsed;

    public String getTestDistribution() {
        return testDistribution;
    }

    private String testDistribution;

    public TestResult(String algorithmName, long runTimeinMins, int numOfHils, String testDistribution) {
        this.algorithmName = algorithmName;
        this.runTimeInSeconds = runTimeinMins;
        this.numberOfHilsUsed = numOfHils;
        this.testDistribution = testDistribution;
    }

    public int getNumberOfHilsUsed() {
        return numberOfHilsUsed;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public long getRunTimeInSeconds() {
        return runTimeInSeconds;
    }

    public static String GetHeader() {
        return "AlgorithmName,RunTimeInSeconds,NumOfHils,TestDistribution";
    }

    @Override
    public String toString() {
        return algorithmName + "," + runTimeInSeconds + "," + numberOfHilsUsed + "," + testDistribution;
    }
}
