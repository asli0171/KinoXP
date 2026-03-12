package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.Seat;
import dk.kinoxp.kinoxp.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ReservationSeatRepository reservationSeatRepository;
    @Mock
    private SeatRepository seatRepository;
    @Mock
    private ScreeningRepository screeningRepository;
    @Mock
    private PricingService pricingService;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void should_throw_exception_when_seat_already_reserved() {
        Screening screening = new Screening ();
        screening.setId(1L);
        Seat seat = new Seat("Standard", 1);
        List<Long> seatIds = List.of(1L);

        when(screeningRepository.findById(1L)).thenReturn(Optional.of(screening));
        when(seatRepository.findById(1L)).thenReturn(Optional.of(seat));

        when(reservationSeatRepository.existsBySeatAndScreening(seat, screening)).thenReturn(true);

        assertThrows(RuntimeException.class, () ->
                reservationService.createReservation(1L, seatIds, "Ana", "ana@email.com")
        );

    }
}