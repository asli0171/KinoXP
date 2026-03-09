package dk.kinoxp.kinoxp.controller;

import dk.kinoxp.kinoxp.dto.ReservationRequestDTO;
import dk.kinoxp.kinoxp.model.Reservation;
import dk.kinoxp.kinoxp.model.ReservationSeat;
import dk.kinoxp.kinoxp.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.status(201).body(reservationService.saveReservation(reservation));
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/screening/{screeningId}")
    public List<ReservationSeat> getReservationsByScreening(@PathVariable Long screeningId) {
        return reservationService.getReservationsByScreeningId(screeningId);
    }

    @PostMapping("/book")
    public Reservation createReservation(@RequestBody ReservationRequestDTO request) {
        return reservationService.createReservation(request.getScreeningId(), request.getSeatId(), request.getCustomerName(), request.getCustomerEmail());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }
}