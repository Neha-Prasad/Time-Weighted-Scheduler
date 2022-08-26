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

    public TestRunner(List<IAlgorithm> algorithms, List<TestData> testData, int numOfHils) {
        this.algorithms = algorithms;
        this.testData = testData;
        this.numOfHils = numOfHils;
    }

    public List<TestResult> RunTests() {
        List<TestResult> results = new ArrayList<>();
        for(IAlgorithm algorithm: this.algorithms) {
            long runDuration = this.RunTest(algorithm);
            TestResult result = new TestResult(algorithm.getName(), runDuration);
            results.add(result);
        }
        return results;
    }

    private long RunTest(IAlgorithm algorithm) {
        ArrayList<TestData> tests = new ArrayList<>();
        for (TestData test: this.testData) {
            tests.add(new TestData(test.getName(), test.getArrivalTime(), test.getExecutionTime()));
        }
        return algorithm.Run(tests, this.numOfHils);
    }
}
