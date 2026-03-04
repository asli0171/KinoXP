package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SEAT")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatType;

    private int seatNumber;

    public Seat() {}

    public Seat(String seatType, int seatNumber) {
        this.setSeatType(seatType);
        this.setSeatNumber(seatNumber);
    }

    public Long getId() {
        return id;
    }

    public String getSeatType (){
        return seatType;
    }

    public void setSeatType (String seatType){
        this.seatType = seatType;
    }

    public int getSeatNumber (){
        return seatNumber;
    }

    public void setSeatNumber (int seatNumber){
        this.seatNumber = seatNumber;
    }
}
