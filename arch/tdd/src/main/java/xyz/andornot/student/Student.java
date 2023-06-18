package xyz.andornot.student;

import lombok.Data;

@Data
public class Student {
    String firstName;

    String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
