package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.repository.ScreeningRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;

    @InjectMocks
    private ScreeningService screeningService;

    @Test
    void should_return_screening_when_found() {
        Screening screening = new Screening();
        screening.setId(1L);

        when(screeningRepository.findById(1L)).thenReturn(Optional.of(screening));

        Optional<Screening> result = screeningService.getScreeningById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void should_return_empty_when_not_found() {
        when(screeningRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Screening> result = screeningService.getScreeningById(99L);

        assertTrue(result.isEmpty());
    }
}