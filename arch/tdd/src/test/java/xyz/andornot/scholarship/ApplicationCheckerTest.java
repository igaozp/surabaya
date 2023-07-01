package xyz.andornot.scholarship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

class ApplicationCheckerTest {
    @Test
    void all_ok() {
        ScholarshipRepository scholarshipRepository = Mockito.mock(ScholarshipRepository.class);
        Mockito.when(scholarshipRepository.find(1024L)).thenReturn(new Scholarship(LocalDate.MAX));

        LocalDate expected = LocalDate.of(2029, 12, 31);
        Mockito.mockStatic(LocalDate.class).when(LocalDate::now).thenReturn(expected);

        ApplicationChecker service = new ApplicationChecker(scholarshipRepository);
        Application application = new Application(775L, 1024L);
        service.checkTime(application);

        Assertions.assertTrue(service.checkTime(application));
    }
}
