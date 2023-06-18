package xyz.andornot.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StudentTest {
    @Test
    void full_name() {
        var student = new Student("Michael", "Jordan");
        Assertions.assertEquals("Michael Jordan", student.getFullName());
    }
}
