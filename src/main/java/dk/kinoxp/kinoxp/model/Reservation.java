package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;

    private int numberOfTickets;

    public Reservation() {}

    public Reservation(String customerEmail, int numberOfTickets) {
        this.setCustomerEmail(customerEmail);
        this.setNumberOfTickets(numberOfTickets);
    }

    public Long getId() {
        return id;
    }

    public String getCustomerEmail (){
        return customerEmail;
    }

    public void setCustomerEmail (String customerEmail){
        this.customerEmail = customerEmail;
    }

    public int getNumberOfTickets (){
        return numberOfTickets;
    }

    public void setNumberOfTickets (int numberOfTickets){
        this.numberOfTickets = numberOfTickets;
    }
}
