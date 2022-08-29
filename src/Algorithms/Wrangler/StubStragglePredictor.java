package Algorithms.Wrangler;

import TestDataGenerator.TestData;

public class StubStragglePredictor implements IStragglePredictor {

    @Override
    public StragglePrediction PredictStraggle(TestData testData) {
        // placeholder random logic. This will be replaced by an actual machine learning model in future work
        if (testData.getArrivalTime() < 5) {
            return new StragglePrediction(true, 25);
        } else if (testData.getArrivalTime() < 10) {
            return new StragglePrediction(false, 50);
        } else if (testData.getArrivalTime() < 15) {
            return new StragglePrediction(true, 75);
        } else if (testData.getArrivalTime() < 20) {
            return new StragglePrediction(false, 100);
        } else {
            return new StragglePrediction(true, 125);
        }
    }
}