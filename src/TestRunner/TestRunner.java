package TestRunner;

import Algorithms.IAlgorithm;
import TestDataGenerator.TestData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestRunner {
    private List<IAlgorithm> algorithms;
    private List<TestData> testData;
    private int numOfHils;
    private int percentageOfLargerTests;
    private int percentageOfSmallerTests;

    public TestRunner(List<IAlgorithm> algorithms, List<TestData> testData, int numOfHils, int percentageOfLargerTests, int percentageOfSmallerTests) {
        this.algorithms = algorithms;
        this.testData = testData;
        this.numOfHils = numOfHils;
        this.percentageOfLargerTests = percentageOfLargerTests;
        this.percentageOfSmallerTests = percentageOfSmallerTests;
    }

    public List<TestResult> RunTests() {
        List<TestResult> results = new ArrayList<>();
        for(IAlgorithm algorithm: this.algorithms) {
            long runDuration = this.RunTest(algorithm);
            TestResult result = new TestResult(algorithm.getName(), runDuration, this.numOfHils, this.getTestDistribution());
            results.add(result);
        }
        return results;
    }

    private long RunTest(IAlgorithm algorithm) {
        ArrayList<TestData> tests = new ArrayList<>();
        for (TestData test: this.testData) {
            tests.add(new TestData(test));
        }
        return algorithm.Run(tests, this.numOfHils);
    }

    public String getTestDistribution() {
        return "%Large_" + this.percentageOfLargerTests + "_%Small_" + this.percentageOfSmallerTests;
    }
}
