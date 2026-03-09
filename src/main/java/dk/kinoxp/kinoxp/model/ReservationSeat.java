package dk.kinoxp.kinoxp.model;
import jakarta.persistence.*;

@Entity
@Table(name = "reservationseat")
public class ReservationSeat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public ReservationSeat() {}

    public ReservationSeat(Seat seat, Reservation reservation, Screening screening){
        this.setSeat(seat);
        this.setReservation(reservation);
        this.setScreening(screening);
    }

    public Long getId() {
        return id;

    }

    public Seat getSeat (){
        return seat;

    }

    public void setSeat (Seat seat){
        this.seat = seat;
    }

    public Screening getScreening(){
        return screening;
    }

    public void setScreening (Screening screening){
        this.screening = screening;
    }


    public Reservation getReservation (){
        return reservation;

    }

    public void setReservation (Reservation reservation){
        this.reservation = reservation;
    }
}