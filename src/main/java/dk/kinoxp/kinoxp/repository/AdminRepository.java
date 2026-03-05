package dk.kinoxp.kinoxp.repository;

import dk.kinoxp.kinoxp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}