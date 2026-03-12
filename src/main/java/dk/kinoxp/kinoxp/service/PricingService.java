package dk.kinoxp.kinoxp.service;

import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.model.Seat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    private int longFilmSurcharge;

    private int surcharge3D;

    private int reservationFee;

    private double groupDiscount;

    private int longFilmThreshold;

    private int groupThreshold;

    private double cowboySurcharge;

    private double sofaSurcharge;

    public PricingService(@Value("${pricing.longfilm.surcharge}") int longFilmSurcharge, @Value("${pricing.3d.surcharge}") int surcharge3D, @Value("${pricing.reservation.fee}") int reservationFee, @Value("${pricing.group.discount}") double groupDiscount, @Value("${pricing.longfilm.threshold}") int longFilmThreshold, @Value("${pricing.group.threshold}") int groupThreshold, @Value("${pricing.seat.cowboy}") double cowboySurcharge, @Value("${pricing.seat.sofa}") double sofaSurcharge) {
        this.longFilmSurcharge = longFilmSurcharge;
        this.surcharge3D = surcharge3D;
        this.reservationFee = reservationFee;
        this.groupDiscount = groupDiscount;
        this.longFilmThreshold = longFilmThreshold;
        this.groupThreshold = groupThreshold;
        this.cowboySurcharge = cowboySurcharge;
        this.sofaSurcharge = sofaSurcharge;

    }


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



