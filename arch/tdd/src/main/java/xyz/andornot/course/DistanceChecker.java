package xyz.andornot.course;

public record DistanceChecker(CourseRepository courseRepository, DistanceCalculator distanceCalculator) {

    public boolean checkDistance(long courseId, Position position) {
        var course = courseRepository.find(courseId);
        var classRoom = course.getClassRoom();

        var distance = distanceCalculator.calculate(position, classRoom.getPosition());

        return distance < 50D;
    }
}
