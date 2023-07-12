package xyz.andornot.scholarship;

import lombok.Data;

import java.util.List;

@Data
public class Transcript {
    private String programType;
    private List<Course> courses;

    public Transcript(String programType, Course... courses) {
        this.programType = programType;
        this.courses = List.of(courses);
    }

    public double calculatorWeightedAverage() {
        var totalCredit = 0.001D;
        var totalWeightedScore = 0D;

        for (Course course : courses) {
            totalCredit += course.getCredit();
            totalWeightedScore += course.getScore() * course.getCredit();
        }

        return totalWeightedScore / totalCredit;
    }

    public boolean hasNoCourses() {
        return courses.isEmpty();
    }
}
