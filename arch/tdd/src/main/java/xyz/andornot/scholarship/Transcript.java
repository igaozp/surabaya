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
}
