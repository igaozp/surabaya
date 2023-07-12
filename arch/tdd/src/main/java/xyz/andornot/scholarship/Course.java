package xyz.andornot.scholarship;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    private String name;
    private int score;
    private int credit;
}
