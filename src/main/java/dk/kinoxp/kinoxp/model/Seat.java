package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatType;

    private Integer seatNumber;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public Seat() {}

    public Seat(String seatType, Integer seatNumber) {
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

    public Integer getSeatNumber (){
        return seatNumber;
    }

    public void setSeatNumber (Integer seatNumber){
        this.seatNumber = seatNumber;
    }


    public Hall getHall (){
        return hall;

    }

    public void setHall (Hall hall){
        this.hall = hall;
    }

}
