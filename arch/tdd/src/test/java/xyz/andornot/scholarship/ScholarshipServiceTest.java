package xyz.andornot.scholarship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScholarshipServiceTest {
    @Test
    void NO_courses() throws ScholarshipService.UnknownProgramTypeException {
        var service = new ScholarshipService();
        var actual = service.calculate(new Transcript());
        Assertions.assertEquals(0, actual);
    }

    @Test
    void full_scholarship() throws ScholarshipService.UnknownProgramTypeException {
        var service = new ScholarshipService();

        var actual = service.calculate(new Transcript(
                new Course("Algorithm", 70, 3),
                new Course("Computer Internet", 80, 2),
                new Course("Operating System", 90, 3)
        ));

        Assertions.assertEquals(10_000, actual);
    }
}
