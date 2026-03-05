package dk.kinoxp.kinoxp.controller;

import dk.kinoxp.kinoxp.model.Reservation;
import dk.kinoxp.kinoxp.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController (ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation){
        return reservationService.saveReservation(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id){
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
    }

}
