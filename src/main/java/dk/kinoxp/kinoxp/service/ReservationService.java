package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Reservation;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
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
