package xyz.andornot.course;

public class DistanceChecker {
    private final CourseRepository courseRepository;
    private final DistanceCalculator distanceCalculator;


    public DistanceChecker(CourseRepository courseRepository, DistanceCalculator distanceCalculator) {
        this.courseRepository = courseRepository;
        this.distanceCalculator = distanceCalculator;
    }

    public boolean checkDistance(long courseId, Position position) {
        var course = courseRepository.find(courseId);
        var classRoom = course.getClassRoom();

        var distance = distanceCalculator.calculate(position, classRoom.getPosition());

        return distance < 50D;
    }
}
