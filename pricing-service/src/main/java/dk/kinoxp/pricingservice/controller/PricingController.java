package dk.kinoxp.pricingservice.controller;

import dk.kinoxp.pricingservice.dto.PricingRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @PostMapping("/calculate")
    public double calculatePrice(@RequestBody PricingRequest request) {
        double basePrice = request.getBasePrice();

        if (request.getFilmLength() > 150) basePrice += 20;
        if (request.isIs3D()) basePrice += 30;

        double total = 0;
        for (String seatType : request.getSeatTypes()) {
            double seatPrice = basePrice;
            if (seatType.equalsIgnoreCase("cowboy")) {
                seatPrice -= seatPrice * 0.10;
            } else if (seatType.equalsIgnoreCase("sofa")) {
                seatPrice += 50;
            }
            total += seatPrice;
        }

        if (request.getNumberOfTickets() <= 5) total += 25;
        if (request.getNumberOfTickets() > 10) total -= total * 0.10;

        return Math.round(total);
    }
}
