package xyz.andornot.course;

public class DistanceChecker {
    private final CourseRepository courseRepository;
    private final DistanceCalculator distanceCalculator;


    public DistanceChecker(CourseRepository courseRepository, DistanceCalculator distanceCalculator) {
        this.courseRepository = courseRepository;
        this.distanceCalculator = distanceCalculator;
    }

    public boolean checkDistance(long courseId, double longitude, double latitude) {
        var course = courseRepository.find(courseId);
        var classRoom = course.getClassRoom();

        var classRoomLongitude = classRoom.getLongitude();
        var classRoomLatitude = classRoom.getLatitude();

        var distance = distanceCalculator.calculate(longitude, latitude, classRoomLongitude, classRoomLatitude);

        return distance < 50D;
    }
}
