package Algorithms.Wrangler;

public class StragglePrediction {
    private boolean Straggle;

    public int getConfidenceScore() {
        return ConfidenceScore;
    }

    private int ConfidenceScore;

    public StragglePrediction(boolean straggle, int confidenceScore) {
        this.Straggle = straggle;
        this.ConfidenceScore = confidenceScore;
    }

    public boolean isStraggle(int confidenceScoreThreshold) {
        if (this.ConfidenceScore > confidenceScoreThreshold) {
            return this.Straggle;
        } else {
            return false;
        }
    }
}
