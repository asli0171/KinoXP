package dk.kinoxp.kinoxp.repository;

import dk.kinoxp.kinoxp.model.Seat;
import dk.kinoxp.kinoxp.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByHall(Hall hall);
}