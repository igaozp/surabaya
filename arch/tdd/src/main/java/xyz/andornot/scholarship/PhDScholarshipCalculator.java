package xyz.andornot.scholarship;

public class PhDScholarshipCalculator implements Calculator {
    @Override
    public int calculate(Transcript transcript) {
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
}