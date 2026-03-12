package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.model.Film;
import dk.kinoxp.kinoxp.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {
    @Mock
    private FilmRepository filmRepository;
    @InjectMocks
    private FilmService filmService;

    @Test
    void test_Get_All_Films(){
        Film film1 = new Film("Inception", "English", "PG13", 180);
        Film film2 = new Film("The Dark Knight", "English", "PG13", 152);
        List<Film> films = List.of(film1, film2);
        when(filmRepository.findAll()).thenReturn(films);
        List<Film> result = filmService.getAllFilms();

        assertEquals(2, result.size());
    }
}