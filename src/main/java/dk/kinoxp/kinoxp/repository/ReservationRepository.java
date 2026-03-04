package dk.kinoxp.kinoxp.repository;

import dk.kinoxp.kinoxp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}