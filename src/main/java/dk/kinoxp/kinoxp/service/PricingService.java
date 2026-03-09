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
        double basePrice = screening.getBasePrice();

        if (screening.getFilm().getLength() > longFilmThreshold) {
            basePrice += longFilmSurcharge;
        }

        if (screening.is3D()) {
            basePrice += surcharge3D;
        }

        double total = 0;
        for (Seat seat : seats) {
            double seatPrice = basePrice;
            if (seat.getSeatType().equalsIgnoreCase("cowboy")) {
                seatPrice -= seatPrice * cowboySurcharge;
            } else if (seat.getSeatType().equalsIgnoreCase("sofa")) {
                seatPrice += sofaSurcharge;
            }
            total += seatPrice;
        }

        if (numberOfTickets <= 5) {
            total += reservationFee;
        }

        if (numberOfTickets > groupThreshold) {
            total -= total * groupDiscount;
        }

        return Math.round(total);
    }
}



