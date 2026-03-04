package dk.kinoxp.kinoxp.model;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "SCREENING")
public class Screening {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int basePrice;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;
    public Screening() {}

    public Screening(int basePrice, Film film, Hall hall, LocalDateTime dateTime){
        this.setBasePrice(basePrice);
        this.setFilm(film);
        this.setHall(hall);
        this.setDateTime(dateTime);
    }

    public Long getId() {
        return id;

    }

    public int getBasePrice (){
        return basePrice;

    }

    public void setBasePrice (int basePrice){
        this.basePrice = basePrice;
    }


    public Film getFilm (){
        return film;

    }

    public void setFilm (Film film){
        this.film = film;
    }


    public Hall getHall (){
        return hall;

    }

    public void setHall (Hall hall ){
        this.hall = hall;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}