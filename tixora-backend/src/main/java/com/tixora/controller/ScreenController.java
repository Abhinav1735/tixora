
package com.tixora.controller;

import com.tixora.dto.ScreenMapper;
import com.tixora.dto.ScreenRequest;
import com.tixora.dto.ScreenResponse;
import com.tixora.entity.Screen;
import com.tixora.service.ScreenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
@CrossOrigin(origins = "*")
public class ScreenController {

    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @GetMapping
    public ResponseEntity<List<ScreenResponse>> getAllScreens() {
        List<ScreenResponse> screens = screenService.getAllScreens()
                .stream()
                .map(ScreenMapper::toResponse)
                .toList();

        return ResponseEntity.ok(screens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenResponse> getScreenById(
            @PathVariable Long id
    ) {
        Screen screen = screenService.getScreenById(id);

        if (screen == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ScreenMapper.toResponse(screen));
    }

    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<ScreenResponse>> getScreensByTheatre(
            @PathVariable Long theatreId
    ) {
        List<ScreenResponse> screens =
                screenService.getScreensByTheatre(theatreId)
                        .stream()
                        .map(ScreenMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(screens);
    }

    @PostMapping
    public ResponseEntity<ScreenResponse> createScreen(
            @Valid @RequestBody ScreenRequest request
    ) {
        Screen screen = screenService.createScreen(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ScreenMapper.toResponse(screen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScreenResponse> updateScreen(
            @PathVariable Long id,
            @Valid @RequestBody ScreenRequest request
    ) {
        Screen screen = screenService.updateScreen(id, request);

        if (screen == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ScreenMapper.toResponse(screen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreen(@PathVariable Long id) {
        boolean deleted = screenService.deleteScreen(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
