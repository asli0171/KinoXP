package dk.kinoxp.kinoxp.dto;

import dk.kinoxp.kinoxp.model.Seat;

public class SeatAvailabilityDTO {
    private Seat seat;
    private boolean isAvailable;

    public SeatAvailabilityDTO(Seat seat, boolean isAvailable) {
        this.seat = seat;
        this.isAvailable = isAvailable;
    }

    public Seat getSeat() { return seat; }
    public void setSeat (Seat seat) { this.seat = seat; }
    public boolean isAvailable() { return isAvailable;}
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable;}
}
