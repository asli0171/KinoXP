package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.dto.SeatAvailabilityDTO;
import dk.kinoxp.kinoxp.model.Hall;
import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.repository.ReservationSeatRepository;
import dk.kinoxp.kinoxp.repository.ScreeningRepository;
import dk.kinoxp.kinoxp.repository.SeatRepository;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    private final SeatRepository seatRepository;
    private final ScreeningRepository screeningRepository;
    private final ReservationSeatRepository reservationSeatRepository;

    public SeatService(SeatRepository seatRepository, ScreeningRepository screeningRepository, ReservationSeatRepository reservationSeatRepository){
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
        this.reservationSeatRepository = reservationSeatRepository;
    }

    public List<SeatAvailabilityDTO> getSeatsWithAvailability(Long screeningId) {
        Screening screening = screeningRepository.findById(screeningId).orElseThrow();
        Hall hall = screening.getHall();
        List<Seat> seats = seatRepository.findByHall(hall);
        List<SeatAvailabilityDTO> result = new ArrayList<>();
        for (Seat seat : seats) {
            boolean taken = reservationSeatRepository.existsBySeatAndScreening(seat, screening);
            result.add(new SeatAvailabilityDTO(seat, !taken));
        }
        return result;
    }

    public Seat saveseat (Seat seat) {
        return seatRepository.save(seat);
    }

    public List<Seat> getAllSeats (){
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatById (Long id){
        return seatRepository.findById(id);
    }

    public void deleteSeatById (Long id){
        seatRepository.deleteById(id);
    }

}
