
package com.tixora.controller;

import com.tixora.dto.MovieMapper;
import com.tixora.dto.MovieRequest;
import com.tixora.dto.MovieResponse;
import com.tixora.entity.Movie;
import com.tixora.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<MovieResponse> movies = movieService.getAllMovies()
                .stream()
                .map(MovieMapper::toResponse)
                .toList();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(
            @PathVariable Long id) {

        Movie movie = movieService.getMovieById(id);

        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(MovieMapper.toResponse(movie));
    }

    @PostMapping
    public ResponseEntity<MovieResponse> addMovie(
            @Valid @RequestBody MovieRequest request) {

        Movie savedMovie = movieService.createMovie(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MovieMapper.toResponse(savedMovie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(
            @PathVariable Long id,
            @Valid @RequestBody MovieRequest request) {

        Movie movie = movieService.updateMovie(id, request);;

        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(MovieMapper.toResponse(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(
            @PathVariable Long id) {

        boolean deleted = movieService.deleteMovie(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
