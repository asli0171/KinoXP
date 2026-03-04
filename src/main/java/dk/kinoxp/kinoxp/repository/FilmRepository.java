package dk.kinoxp.kinoxp.repository;

import dk.kinoxp.kinoxp.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}