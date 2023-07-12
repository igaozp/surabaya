package xyz.andornot.scholarship;

import lombok.Data;

import java.util.List;

@Data
public class Transcript {
    private List<Course> courses;
    private String programType;

    public Transcript(Course... courses) {
        this.courses = List.of(courses);
    }
}
