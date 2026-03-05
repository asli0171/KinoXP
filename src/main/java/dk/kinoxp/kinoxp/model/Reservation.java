package dk.kinoxp.kinoxp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;

    private String customerName;

    private int numberOfTickets;

    private double totalPrice;

    public Reservation() {}

    public Reservation(String customerEmail, String customerName, int numberOfTickets, double totalPrice) {
        this.setCustomerEmail(customerEmail);
        this.setNumberOfTickets(numberOfTickets);
        this.setCustomerName(customerName);
        this.setTotalPrice(totalPrice);
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

    public String getCustomerName (){
        return customerName;
    }

    public void setCustomerName (String customerName){
        this.customerName = customerName;
    }

    public int getNumberOfTickets (){
        return numberOfTickets;
    }

    public void setNumberOfTickets (int numberOfTickets){
        this.numberOfTickets = numberOfTickets;
    }

    public double getTotalPrice (){
        return totalPrice;
    }

    public void setTotalPrice (double totalPrice){
        this.totalPrice = totalPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
