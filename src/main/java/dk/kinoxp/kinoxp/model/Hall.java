package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "HALL")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hallnumber")
    private Integer hallNumber;

    public Hall() {}

    public Hall(Integer hallNumber) {
        this.setHallNumber(hallNumber);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHallNumber() {
        return hallNumber;
    }
    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }
}
