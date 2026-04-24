package dk.kinoxp.pricingservice.dto;

import java.util.List;

public class PricingRequest {
    private double basePrice;
    private boolean is3D;
    private int filmLength;
    private int numberOfTickets;
    private List<String> seatTypes;

    public double getBasePrice() { return basePrice; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public boolean isIs3D() { return is3D; }
    public void setIs3D(boolean is3D) { this.is3D = is3D; }
    public int getFilmLength() { return filmLength; }
    public void setFilmLength(int filmLength) { this.filmLength = filmLength; }
    public int getNumberOfTickets() { return numberOfTickets; }
    public void setNumberOfTickets(int numberOfTickets) { this.numberOfTickets = numberOfTickets; }
    public List<String> getSeatTypes() { return seatTypes; }
    public void setSeatTypes(List<String> seatTypes) { this.seatTypes = seatTypes; }
}