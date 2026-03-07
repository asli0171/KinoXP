package dk.kinoxp.kinoxp.service;
import dk.kinoxp.kinoxp.model.Seat;
import dk.kinoxp.kinoxp.repository.ReservationRepository;
import dk.kinoxp.kinoxp.repository.ScreeningRepository;
import dk.kinoxp.kinoxp.repository.SeatRepository;
import dk.kinoxp.kinoxp.repository.ReservationSeatRepository;
import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.ReservationSeat;
import dk.kinoxp.kinoxp.service.PricingService;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Reservation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationSeatRepository reservationSeatRepository;
    private final SeatRepository seatRepository;
    private final ScreeningRepository screeningRepository;
    private final PricingService pricingService;

    public ReservationService(ReservationRepository reservationRepository, ReservationSeatRepository reservationSeatRepository, SeatRepository seatRepository, ScreeningRepository screeningRepository, PricingService pricingService){
        this.reservationRepository = reservationRepository;
        this.reservationSeatRepository = reservationSeatRepository;
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
        this.pricingService = pricingService;

    }

    public Reservation createReservation(Long screeningId, List<Long> seatIds, String customerName, String customerEmail){
        Screening screening = screeningRepository.findById(screeningId).orElseThrow();
        List<Seat> seats = seatIds.stream()
                .map(id -> seatRepository.findById(id).orElseThrow())
                .collect(Collectors.toList());
        for (Seat seat : seats) {
            if (reservationSeatRepository.existsBySeatAndScreening(seat, screening)) {
                throw new RuntimeException("Seat already reserved: " + seat.getId());
            }
        }

        double totalPrice = pricingService.calculatePrice(screening, seatIds.size(), seats);
        Reservation reservation = new Reservation(customerEmail, customerName, seatIds.size(), totalPrice);
        reservationRepository.save(reservation);
        for (Seat seat : seats) {
            ReservationSeat reservationSeat = new ReservationSeat(seat, reservation, screening);
            reservationSeatRepository.save(reservationSeat);
        }
        return reservation;

    }

    public Reservation saveReservation (Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations (){
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById (Long id){
        return reservationRepository.findById(id);
    }

    public void deleteReservationById (Long id){
        reservationRepository.deleteById(id);
    }

}
