package xyz.andornot.scholarship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScholarshipServiceTest {
    @Test
    void NO_courses() {
        var service = new ScholarshipService();
        var actual = service.calculate(new Transcript());
        Assertions.assertEquals(0, actual);
    }

    @Test
    void full_scholarship() {
        var service = new ScholarshipService();

        var actual = service.calculate(new Transcript(
                new Course("Algorithm", 70),
                new Course("Computer Internet", 80),
                new Course("Operating System", 90)
        ));

        Assertions.assertEquals(10_000, actual);
    }
}
