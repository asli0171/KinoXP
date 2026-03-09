package dk.kinoxp.kinoxp.repository;

import dk.kinoxp.kinoxp.model.Seat;
import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationSeatRepository extends JpaRepository<ReservationSeat, Long> {
    boolean existsBySeatAndScreening(Seat seat, Screening screening);
    List<ReservationSeat> findByScreeningId(Long screeningId);
}