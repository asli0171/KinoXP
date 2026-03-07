package dk.kinoxp.kinoxp.controller;


import dk.kinoxp.kinoxp.dto.SeatAvailabilityDTO;
import dk.kinoxp.kinoxp.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")

public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/screening/{screeningId}")
    public List<SeatAvailabilityDTO> getSeatsForScreening(@PathVariable Long screeningId) {
        return seatService.getSeatsWithAvailability(screeningId);
    }

}
