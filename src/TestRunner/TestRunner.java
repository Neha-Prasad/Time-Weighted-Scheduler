package TestRunner;

import Algorithms.IAlgorithm;
import TestDataGenerator.TestData;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private List<IAlgorithm> algorithms;
    private List<TestData> testData;

    public TestRunner(List<IAlgorithm> algorithms, List<TestData> testData) {
        this.algorithms = algorithms;
        this.testData = testData;
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
        Instant start = Instant.now();
        algorithm.Run(this.testData);
        Instant end = Instant.now();
        return Duration.between(start, end).toSeconds();
    }
}
