package TestDataGenerator;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {
    private Random random;
    private int numberOfTests = 5000; // Keeping this constant to create level field
    private int percentageOfLargerTests; // larger tests take more than 10 mins upto 60 mins
    private int percentageOfSmallerTests; // smaller tests take less than 10 mins

    public TestDataGenerator(int percentageOfLargerTests, int percentageOfSmallerTests) {
        this.random = new Random();
        this.percentageOfLargerTests = percentageOfLargerTests;
        this.percentageOfSmallerTests = percentageOfSmallerTests;
    }

    public List<TestData> Generate()
    {
        List<TestData> testData = new ArrayList<TestData>();
        // Generate test data with larger execution time
        int numberOfLargeTests = (int) ((int) this.percentageOfLargerTests * .01 * this.numberOfTests);
        for (int i = 0;i < numberOfLargeTests; i++) {
            testData.add(new TestData("TestCase" + i, random.nextInt(11, 61)));
        }

        // Generate test data with smaller execution time
        int numberOfSmallTests = (int) ((int) this.percentageOfSmallerTests * .01 * this.numberOfTests);
        for (int i = 0;i < numberOfSmallTests; i++) {
            testData.add(new TestData("TestCase" + i, random.nextInt(1, 11)));
        }

        Collections.shuffle(testData);
        return testData;
    }
}