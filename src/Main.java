import Algorithms.*;
import Algorithms.Genetic.Genetic;
import Algorithms.Wrangler.Wrangler;
import TestDataGenerator.TestDataGenerator;
import TestDataGenerator.TestData;
import TestRunner.*;
import Utils.ChartWriter;
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
        algorithms.add(new Wrangler());

        int[] numberOfHils = new int[] {25, 50, 150, 500};
        for (int num: numberOfHils) {
            System.out.println("Preparing TestRunner and running tests with " + num + " HIL availability");
            TestRunner testRunner = new TestRunner(algorithms, testData, num);
            List<TestResult> results = testRunner.RunTests();

            String fileName = "TestResults" + suffix + "_NumOfHils_" + num;
            System.out.println("Writing test results to file" + fileName + ".csv");
            CsvWriter<TestResult> csvResultsWriter = new CsvWriter<TestResult>(fileName + ".csv");
            csvResultsWriter.Write(results);

            System.out.println("Writing chart to file" + fileName+ ".jpeg");
            ChartWriter.Write(fileName, results);
        }
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
