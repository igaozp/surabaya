package xyz.andornot.scholarship;

import org.springframework.stereotype.Component;

@Component
public class ApplyScholarshipService {
    private final ApplicationChecker applicationChecker;
    private final ApplicationRepository applicationRepository;

    public ApplyScholarshipService(ApplicationChecker applicationChecker, ApplicationRepository applicationRepository) {
        this.applicationChecker = applicationChecker;
        this.applicationRepository = applicationRepository;
    }

    public void apply(Application application) {
        if (this.applicationChecker.checkTime(application)) {
            this.applicationRepository.create(application);
        }
    }
}
