
package com.tixora.controller;

import com.tixora.dto.TheatreMapper;
import com.tixora.dto.TheatreRequest;
import com.tixora.dto.TheatreResponse;
import com.tixora.entity.Theatre;
import com.tixora.service.TheatreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
@CrossOrigin(origins = "*")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public ResponseEntity<List<TheatreResponse>> getAllTheatres() {
        List<TheatreResponse> theatres = theatreService.getAllTheatres()
                .stream()
                .map(TheatreMapper::toResponse)
                .toList();

        return ResponseEntity.ok(theatres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreResponse> getTheatreById(
            @PathVariable Long id
    ) {
        Theatre theatre = theatreService.getTheatreById(id);

        if (theatre == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(TheatreMapper.toResponse(theatre));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<TheatreResponse>> getTheatresByCity(
            @PathVariable String city
    ) {
        List<TheatreResponse> theatres = theatreService.getTheatresByCity(city)
                .stream()
                .map(TheatreMapper::toResponse)
                .toList();

        return ResponseEntity.ok(theatres);
    }

    @PostMapping
    public ResponseEntity<TheatreResponse> createTheatre(
            @Valid @RequestBody TheatreRequest request
    ) {
        Theatre theatre = theatreService.createTheatre(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TheatreMapper.toResponse(theatre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheatreResponse> updateTheatre(
            @PathVariable Long id,
            @Valid @RequestBody TheatreRequest request
    ) {
        Theatre theatre = theatreService.updateTheatre(id, request);

        if (theatre == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(TheatreMapper.toResponse(theatre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
        boolean deleted = theatreService.deleteTheatre(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
