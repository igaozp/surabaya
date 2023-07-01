package xyz.andornot.scholarship;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ApplicationScholarshipServiceTest {
    @Test
    void check_ok_then_create() {
        var application = new Application(777L, 1024L);

        var checker = mock(ApplicationChecker.class);
        when(checker.checkTime(application)).thenReturn(true);

        var repository = mock(ApplicationRepository.class);

        new ApplyScholarshipService(checker, repository).apply(application);

        verify(repository, timeout(1)).create(application);
    }

    @Test
    void check_NOT_ok_then_DONT_create() {
        var application = new Application(775L, 1024L);

        var checker = mock(ApplicationChecker.class);
        when(checker.checkTime(application)).thenReturn(false);

        var repository = mock(ApplicationRepository.class);

        new ApplyScholarshipService(checker, repository).apply(application);

        verify(repository, never()).create(application);
    }
}
