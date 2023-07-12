package xyz.andornot.scholarship;

public class MasterScholarshipCalculator {
    static int calculateMaster(Transcript transcript) {
        var courses = transcript.getCourses();
        if (courses.isEmpty()) {
            return 0;
        }

        var totalCredit = 0.001D;
        var totalWeightedScore = 0D;

        for (Course course : courses) {
            totalCredit += course.getCredit();
            totalWeightedScore += course.getScore() * course.getCredit();
        }

        var weightedAverage = totalWeightedScore / totalCredit;

        if (weightedAverage >= 90D) {
            return 15_000;
        } else if (weightedAverage >= 80D) {
            return 7500;
        } else {
            return 0;
        }
    }
}