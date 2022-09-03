package TestDataGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {
    private Random random;
    private int numberOfTests = 5000; // Keeping this constant to create level field
    private int percentageOfLargerTests; // larger tests take more than 10 seconds up to 60 seconds
    private int percentageOfSmallerTests; // smaller tests take less than 10 seconds

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
            testData.add(new TestData("TestCase" + i, random.nextInt(0, 25), random.nextInt(11, 61), this.getTestDistribution()));
        }

        // Generate test data with smaller execution time
        int numberOfSmallTests = (int) ((int) this.percentageOfSmallerTests * .01 * this.numberOfTests);
        for (int i = 0;i < numberOfSmallTests; i++) {
            testData.add(new TestData("TestCase" + i, random.nextInt(0, 25), random.nextInt(1, 11), this.getTestDistribution()));
        }

        Collections.shuffle(testData);
        return testData;
    }

    private String getTestDistribution() {
        return "%Large_" + percentageOfLargerTests + "_%Small_" + percentageOfSmallerTests;
    }
}
