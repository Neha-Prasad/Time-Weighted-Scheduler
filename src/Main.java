import Algorithms.*;
import Algorithms.Genetic.Genetic;
import TestDataGenerator.TestDataGenerator;
import TestDataGenerator.TestData;
import TestRunner.*;
import Utils.CsvWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void Run(int percentageOfLargerTests, int percentageOfSmallerTests) {
        String suffix = "_%OfLargeTests_" + percentageOfLargerTests + "_%OfSmallTests_" + percentageOfSmallerTests;
        System.out.println("Preparing Test Data with " + suffix);
        TestDataGenerator generator = new TestDataGenerator(percentageOfLargerTests, percentageOfSmallerTests);;
        List<TestData> testData = generator.Generate();

        System.out.println("Writing test data being used to file - TestData" + suffix + ".csv");
        CsvWriter<TestData> csvWriter = new CsvWriter<TestData>("TestData" + suffix + ".csv");
        csvWriter.Write(testData);

        System.out.println("Preparing algorithms to run");
        List<IAlgorithm> algorithms = new ArrayList<>();
        algorithms.add(new ShortestJobFirstPreemptive());
        algorithms.add(new ShortestJobFirstNonPreemptive());
        algorithms.add(new Genetic());
        // algorithms.add(new Wrangler());

        System.out.println("Preparing TestRunner and running tests with 100 HIL availability");
        TestRunner testRunner = new TestRunner(algorithms, testData, 25);
        List<TestResult> results = testRunner.RunTests();

        System.out.println("Writing test results to file - TestResults" + suffix + ".csv");
        CsvWriter<TestResult> csvResultsWriter = new CsvWriter<TestResult>("TestResults" + suffix + ".csv");
        csvResultsWriter.Write(results);
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>() {{
            put(0, 100);
            put(100, 0);
            put(50, 50);
            put(25, 75);
            put(75, 25);
        }};

        for(Map.Entry<Integer, Integer> set : map.entrySet()) {
            Run(set.getKey(), set.getValue());
        }
    }
}
