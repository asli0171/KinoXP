package dk.kinoxp.kinoxp.repository;

import dk.kinoxp.kinoxp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import dk.kinoxp.kinoxp.model.Customer;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomer(Customer customer);
}