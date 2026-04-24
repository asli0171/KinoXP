package dk.kinoxp.kinoxp.controller;

import dk.kinoxp.kinoxp.dto.ReservationRequestDTO;
import dk.kinoxp.kinoxp.model.Customer;
import dk.kinoxp.kinoxp.model.Reservation;
import dk.kinoxp.kinoxp.model.ReservationSeat;
import dk.kinoxp.kinoxp.repository.CustomerRepository;
import dk.kinoxp.kinoxp.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomerRepository customerRepository;

    public ReservationController(ReservationService reservationService,
                                 CustomerRepository customerRepository) {
        this.reservationService = reservationService;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ResponseEntity.ok(reservationService.getAllReservations());
        }

        return customerRepository.findByUsername(auth.getName())
                .map(customer -> ResponseEntity.ok(
                        reservationService.getReservationsByCustomer(customer)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(
            @PathVariable Long id, Authentication auth) {

        return reservationService.getReservationById(id)
                .map(res -> {
                    boolean isAdmin = auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                    boolean isOwner = res.getCustomer() != null &&
                            res.getCustomer().getUsername().equals(auth.getName());

                    if (isAdmin || isOwner) {
                        return ResponseEntity.ok(res);
                    }
                    return ResponseEntity.status(403).build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/book")
    public ResponseEntity<Reservation> bookReservation(
            @RequestBody ReservationRequestDTO request, Authentication auth) {

        Customer customer = customerRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("Kunde ikke fundet"));

        Reservation reservation = reservationService.createReservation(
                request.getScreeningId(),
                request.getSeatId(),
                request.getCustomerName(),
                request.getCustomerEmail()
        );

        reservation.setCustomer(customer);
        reservationService.saveReservation(reservation);

        return ResponseEntity.status(201).body(reservation);
    }

    @GetMapping("/screening/{screeningId}")
    public List<ReservationSeat> getReservationsByScreening(@PathVariable Long screeningId) {
        return reservationService.getReservationsByScreeningId(screeningId);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.status(201).body(reservationService.saveReservation(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable Long id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }
}