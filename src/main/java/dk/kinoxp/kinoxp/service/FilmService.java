package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.repository.FilmRepository;
import org.springframework.stereotype.Service;
import dk.kinoxp.kinoxp.model.Film;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository){
        this.filmRepository = filmRepository;
    }

    public Film savefilm (Film film) {
        return filmRepository.save(film);
    }

    public List<Film> getAllFilms (){
        return filmRepository.findAll();
    }

    public Optional<Film> getFilmById (Long id){
        return filmRepository.findById(id);
    }

    public void deleteFilmById (Long id){
        filmRepository.deleteById(id);
    }
}
