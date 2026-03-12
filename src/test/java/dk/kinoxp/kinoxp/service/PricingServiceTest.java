package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.model.Film;
import dk.kinoxp.kinoxp.model.Hall;
import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.Seat;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {

    PricingService pricingService = new PricingService(20, 30, 15, 0.07, 170, 5, 0.10, 0.10);

    @Test
    void should_add_longFilm_surcharge() {
        Film film = new Film("Inception", "English", "PG13", 180);
        Hall hall = new Hall(1);
        Screening screening = new Screening(100, film, hall, LocalDateTime.now(), false);
        Seat seat = new Seat("Standard", 1);

        double result = pricingService.calculatePrice(screening, 1, List.of(seat));

        assertEquals(135, result);

    }

    @Test
    void should_add_group_discount() {
        Film film = new Film("The Dark Knight", "English", "PG13", 152);
        Hall hall = new Hall(2);
        Screening screening = new Screening(100, film, hall, LocalDateTime.now(), false);
        List<Seat> seats = List.of(
                new Seat("Standard", 1),
                new Seat("Standard", 2),
                new Seat("Standard", 3),
                new Seat("Standard", 4),
                new Seat("Standard", 5),
                new Seat("Standard", 6)
        );

        double result = pricingService.calculatePrice(screening, 6, seats);

        assertEquals(558, result);
    }

    @Test
    void should_add_reservation_fee_for_five_or_fewer_tickets() {
        Film film = new Film("Parasite", "Korean", "R", 132);
        Hall hall = new Hall(1);
        Screening screening = new Screening(100, film, hall, LocalDateTime.now(), false);
        List<Seat> seats = List.of(
                new Seat("Standard", 1),
                new Seat("Standard", 2),
                new Seat("Standard", 3)
        );

        double result = pricingService.calculatePrice(screening, 3, seats);

        assertEquals(315, result);
    }


}


