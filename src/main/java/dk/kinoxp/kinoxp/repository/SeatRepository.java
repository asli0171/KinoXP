package dk.kinoxp.kinoxp.repository;

import dk.kinoxp.kinoxp.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}