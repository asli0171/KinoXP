package dk.kinoxp.kinoxp.controller;

import dk.kinoxp.kinoxp.service.HallService;
import dk.kinoxp.kinoxp.model.Hall;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping
    public ResponseEntity<Hall> createHall(@RequestBody Hall hall) {
        return ResponseEntity.status(201).body(hallService.savehall(hall));
    }

    @GetMapping
    public List<Hall> getAllHalls() {
        return hallService.getAllHalls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hall> getHallById(@PathVariable Long id) {
        return hallService.getHallById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHallById(@PathVariable Long id) {
        hallService.deleteHallById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hall> updateHall(@PathVariable Long id, @RequestBody Hall hall) {
        return hallService.getHallById(id)
                .map(existing -> {
                    hall.setId(id);
                    return ResponseEntity.ok(hallService.savehall(hall));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}