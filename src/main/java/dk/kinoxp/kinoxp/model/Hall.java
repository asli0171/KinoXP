package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "HALL")
public class Hall {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hallnumber;

    public Hall() {}

    public Hall(int hallnumber){
        this.setHallnumber(hallnumber);
    }

    public int getHallnumber (){
        return hallnumber;

    }

    public void setHallnumber (int hallnumber){
        this.hallnumber = hallnumber;
    }
}