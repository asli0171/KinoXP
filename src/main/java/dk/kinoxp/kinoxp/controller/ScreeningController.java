package dk.kinoxp.kinoxp.controller;

import dk.kinoxp.kinoxp.model.Screening;
import dk.kinoxp.kinoxp.service.ScreeningService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")

public class ScreeningController {
    private final ScreeningService screeningService;

    public ScreeningController (ScreeningService screeningService){
        this.screeningService = screeningService;
    }

    @PostMapping
    public ResponseEntity<Screening> createScreening(@RequestBody Screening screening) {
        return ResponseEntity.status(201).body(screeningService.saveScreening(screening));

    }

    @GetMapping
    public List<Screening> getAllScreenings(){
        return screeningService.getAllScreenings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Screening> getScreeningById(@PathVariable Long id){
        return screeningService.getScreeningById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreeningById(@PathVariable Long id) {
        screeningService.deleteScreeningById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Screening> updateScreening(@PathVariable Long id, @RequestBody Screening screening) {
        return screeningService.getScreeningById(id)
                .map(existing -> {
                    screening.setId(id);
                    return ResponseEntity.ok(screeningService.saveScreening(screening));
                })
                .orElse(ResponseEntity.notFound().build());

    }

}
