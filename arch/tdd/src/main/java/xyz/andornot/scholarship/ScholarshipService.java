package xyz.andornot.scholarship;

public class ScholarshipService {

    public int calculate(Transcript transcript) throws UnknownProgramTypeException {

        var programType = transcript.getProgramType();

        if (programType.equals("Bachelor")) {
            var courses = transcript.getCourses();
            if (courses.isEmpty()) {
                return 0;
            }

            var total = courses.size();
            var achieved = 0;
            for (Course course : courses) {
                if (course.getScore() >= 0) {
                    achieved++;
                }
            }

            var rate = (double) achieved / total;

            if (rate >= (double) 1 / 2) {
                return 10_000;
            } else if (rate >= (double) 1 / 3) {
                return 5_000;
            } else {
                return 0;
            }
        }

        if (programType.equals("Master")) {
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

        if (programType.equals("PhD")) {
            var courses = transcript.getCourses();
            if (courses.isEmpty()) {
                return 0;
            }

            for (Course course : courses) {
                if (course.getScore() < 80) {
                    return 0;
                }
                if (course.getScore() < 90) {
                    return 20_000;
                }
            }
            return 40_000;
        }

        throw new UnknownProgramTypeException(programType);
    }

    public static class UnknownProgramTypeException extends Throwable {
        public UnknownProgramTypeException(String programType) {
            super("Unknown program type: " + programType);
        }
    }
}
