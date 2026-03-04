package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.repository.SeatRepository;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Seat;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
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
