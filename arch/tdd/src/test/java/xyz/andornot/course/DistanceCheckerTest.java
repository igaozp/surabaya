package xyz.andornot.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DistanceCheckerTest {
    @Test
    void close_enough() {
        var distanceChecker = new DistanceChecker(
                dummy_repository(9527L, 0D, 0D),
                dummy_calculator(1D, 1D, 0D, 0D, 49D)
        );

        Assertions.assertTrue(distanceChecker.checkDistance(9527L, 1D, 1D));
    }

    @Test
    void too_far() {
        var distanceChecker = new DistanceChecker(
                dummy_repository(9527L, 0D, 0D),
                dummy_calculator(99D, 99D, 0D, 0D, 51D)
        );

        Assertions.assertTrue(distanceChecker.checkDistance(9527L, 99D, 99D));
    }

    private CourseRepository dummy_repository(long courseId, double longitude, double latitude) {
        var course = new Course(new ClassRoom(longitude, latitude));

        CourseRepository repository = Mockito.mock(CourseRepository.class);

        Mockito.when(repository.find(courseId)).thenReturn(course);

        return repository;
    }

    private DistanceCalculator dummy_calculator(double distance, double longitude1, double latitude1, double longitude2, double latitude2) {
        DistanceCalculator calculator = Mockito.mock(DistanceCalculator.class);

        Mockito.when(calculator.calculate(longitude1, latitude1, longitude2, latitude2)).thenReturn(distance);

        return calculator;
    }
}
