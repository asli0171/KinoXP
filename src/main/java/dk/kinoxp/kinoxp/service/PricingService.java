package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.Seat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    @Value("${pricing.longfilm.surcharge}")
    private int longFilmSurcharge;

    @Value("${pricing.3d.surcharge}")
    private int surcharge3D;

    @Value("${pricing.reservation.fee}")
    private int reservationFee;

    @Value("${pricing.group.discount}")
    private double groupDiscount;

    @Value("${pricing.longfilm.threshold}")
    private int longFilmThreshold;

    @Value("${pricing.group.threshold}")
    private int groupThreshold;

    @Value("${pricing.seat.cowboy}")
    private double cowboySurcharge;

    @Value("${pricing.seat.sofa}")
    private double sofaSurcharge;

    public double calculatePrice(Screening screening, int numberOfTickets, List<Seat> seats) {
        double price = screening.getBasePrice();

        if (screening.getFilm().getLength() > longFilmThreshold) {
            price += longFilmSurcharge;}

        if (screening.is3D()) {
            price += surcharge3D;}

        for (Seat seat : seats) {
            if (seat.getSeatType().equals("cowboy")) {
                price -= price * cowboySurcharge;
            } else if (seat.getSeatType().equals("sofa")) {
                price += sofaSurcharge;}
        }

        if (numberOfTickets < groupThreshold) {
            price += reservationFee;
        }

        if (numberOfTickets >= groupThreshold) {
            price -= price * groupDiscount;
        }

        return price;
    }
}





