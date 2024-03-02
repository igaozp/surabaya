package xyz.andornot.scholarship;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ApplicationChecker {
    private final ScholarshipRepository scholarshipRepository;

    public ApplicationChecker(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public boolean checkTime(Application application) {
        var scholarship = scholarshipRepository.find(application.scholarshipId());
        var deadline = scholarship.getDeadline();
        var today = LocalDate.now();
        return today.isEqual(deadline) || today.isBefore(deadline);
    }
}
