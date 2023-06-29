package xyz.andornot.scholarship;

public class ScholarshipService {

    public int calculate(Transcript transcript) {
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
}
