package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.model.Seat;
import dk.kinoxp.kinoxp.repository.ReservationRepository;
import dk.kinoxp.kinoxp.repository.ScreeningRepository;
import dk.kinoxp.kinoxp.repository.SeatRepository;
import dk.kinoxp.kinoxp.repository.ReservationSeatRepository;
import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.ReservationSeat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dk.kinoxp.kinoxp.model.Reservation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import dk.kinoxp.kinoxp.model.Customer;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final SeatRepository seatRepository;
    private final ScreeningRepository screeningRepository;
    private final PricingClient pricingClient;

    public ReservationService(ReservationRepository reservationRepository,
                              ReservationSeatRepository reservationSeatRepository,
                              SeatRepository seatRepository,
                              ScreeningRepository screeningRepository,
                              PricingClient pricingClient) {
        this.reservationRepository = reservationRepository;
        this.reservationSeatRepository = reservationSeatRepository;
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
        this.pricingClient = pricingClient;
    }

    public Reservation createReservation(Long screeningId, List<Long> seatIds,
                                         String customerName, String customerEmail) {
        Screening screening = screeningRepository.findById(screeningId).orElseThrow();
        List<Seat> seats = seatIds.stream()
                .map(id -> seatRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());

        for (Seat seat : seats) {
            if (reservationSeatRepository.existsBySeatAndScreening(seat, screening)) {
                throw new RuntimeException("Seat already reserved: " + seat.getId());
            }
        }

        List<String> seatTypes = seats.stream()
                .map(Seat::getSeatType)
                .collect(Collectors.toList());

        double totalPrice = pricingClient.calculatePrice(
                screening.getBasePrice(),
                screening.is3D(),
                screening.getFilm().getLength(),
                seatIds.size(),
                seatTypes
        );

        Reservation reservation = new Reservation(customerEmail, customerName, seatIds.size(), totalPrice);
        reservationRepository.save(reservation);

        for (Seat seat : seats) {
            ReservationSeat reservationSeat = new ReservationSeat(seat, reservation, screening);
            reservationSeatRepository.save(reservationSeat);
        }
        return reservation;
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public List<Reservation> getReservationsByCustomer(Customer customer) {
        return reservationRepository.findByCustomer(customer);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<ReservationSeat> getReservationsByScreeningId(Long screeningId) {
        return reservationSeatRepository.findByScreeningId(screeningId);
    }
}