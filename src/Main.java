import TestDataGenerator.TestDataGenerator;
import TestDataGenerator.TestData;
import Utils.CsvWriter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Arrange
        int option = ParseArguments(args);
        TestDataGenerator generator = null;
        switch (option) {
            case 1: generator = new TestDataGenerator(0, 100); break;
            case 2: generator = new TestDataGenerator(100, 0); break;
            case 3: generator = new TestDataGenerator(50, 50); break;
            case 4: generator = new TestDataGenerator(25, 75); break;
            case 5: generator = new TestDataGenerator(75, 25); break;
        }
        List<TestData> testData = generator.Generate();

        //Write test data being used to csv
        CsvWriter<TestData> csvWriter = new CsvWriter<TestData>("TestData.csv");
        csvWriter.Write(testData);

        // Act

        // Assert
    }

    private static int ParseArguments(String[] args) {
        String usage = "1 -> Percentage of larger tests: 0, Percentage of Smaller tests: 100; "
                + "2 -> Percentage of larger tests: 100, Percentage of Smaller tests: 0; "
                + "3 -> Percentage of larger tests: 50, Percentage of Smaller tests: 50; "
                + "4 -> Percentage of larger tests: 25, Percentage of Smaller tests: 75; "
                + "5 -> Percentage of larger tests: 75, Percentage of Smaller tests: 25; ";

        if (args.length != 1 || args[0].isEmpty()) {
            throw new IllegalArgumentException(usage);
        }

        try {
            int option = Integer.parseInt(args[0]);
            if (option < 1 || option >5 ) {
                throw new Exception();
            }
            return option;
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(usage);
        }
    }
}
