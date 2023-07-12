package xyz.andornot.scholarship;

public class MasterScholarshipCalculator implements Calculator {
    @Override
    public int calculate(Transcript transcript) {
        if (transcript.hasNoCourses()) {
            return 0;
        }

        var weightedAverage = transcript.calculatorWeightedAverage();

        if (weightedAverage >= 90D) {
            return 15_000;
        } else if (weightedAverage >= 80D) {
            return 7500;
        } else {
            return 0;
        }
    }
}