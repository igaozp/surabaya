package xyz.andornot.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DistanceCheckerTest {
    @Test
    void close_enough() {
        var distanceChecker = new DistanceChecker(
                dummy_repository(9527L, new Position(0D, 0D)),
                dummy_calculator(1D, new Position(1D, 0D), new Position(0D, 49D))
        );

        Assertions.assertTrue(distanceChecker.checkDistance(9527L, new Position(1D, 1D)));
    }

    @Test
    void too_far() {
        var distanceChecker = new DistanceChecker(
                dummy_repository(9527L, new Position(0D, 0D)),
                dummy_calculator(99D, new Position(99D, 0D), new Position(0D, 51D))
        );

        Assertions.assertTrue(distanceChecker.checkDistance(9527L, new Position(99D, 99D)));
    }

    private CourseRepository dummy_repository(long courseId, Position position) {
        var course = new Course(new ClassRoom(position));

        CourseRepository repository = Mockito.mock(CourseRepository.class);

        Mockito.when(repository.find(courseId)).thenReturn(course);

        return repository;
    }

    private DistanceCalculator dummy_calculator(double distance, Position position1, Position position2) {
        DistanceCalculator calculator = Mockito.mock(DistanceCalculator.class);

        Mockito.when(calculator.calculate(position1, position2)).thenReturn(distance);

        return calculator;
    }
}
