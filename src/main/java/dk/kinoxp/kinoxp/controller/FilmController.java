package dk.kinoxp.kinoxp.controller;

import dk.kinoxp.kinoxp.service.FilmService;
import dk.kinoxp.kinoxp.model.Film;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")

public class FilmController {
    private final FilmService filmService;

    public FilmController (FilmService filmService){
        this.filmService = filmService;
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film){
        return filmService.saveFilm(film);
    }

    @GetMapping
    public List<Film> getAllFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id){
        return filmService.getFilmById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteFilmById(@PathVariable Long id) {
        filmService.deleteFilmById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @RequestBody Film film) {
        return filmService.getFilmById(id)
                .map(existing -> {
                    film.setId(id);
                    return ResponseEntity.ok(filmService.saveFilm(film));
                })
                .orElse(ResponseEntity.notFound().build());

    }

}
