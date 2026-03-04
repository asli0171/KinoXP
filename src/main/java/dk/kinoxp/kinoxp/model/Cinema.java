package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CINEMA")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Cinema() {}

    public Cinema(String name) {
        this.setName(name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}