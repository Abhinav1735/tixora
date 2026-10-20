
package com.tixora.service;

import com.tixora.entity.Movie;
import com.tixora.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tixora.exception.DuplicateMovieException;

import com.tixora.dto.MovieRequest;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElse(null);
    }



    @Transactional
    public Movie createMovie(MovieRequest request) {

        boolean duplicateExists =
                movieRepository
                        .existsByTitleIgnoreCaseAndGenreIgnoreCaseAndLanguageIgnoreCaseAndDurationMinutes(
                                request.getTitle().trim(),
                                request.getGenre().trim(),
                                request.getLanguage().trim(),
                                request.getDurationMinutes()
                        );

        if (duplicateExists) {
            throw new DuplicateMovieException(
                    "A movie with the same title, genre, language, and duration already exists."
            );
        }

        Movie movie = new Movie();
        movie.setTitle(request.getTitle().trim());
        movie.setGenre(request.getGenre().trim());
        movie.setLanguage(request.getLanguage().trim());
        movie.setDurationMinutes(request.getDurationMinutes());
        movie.setRating(request.getRating());

        return movieRepository.save(movie);
    }





    @Transactional
    public Movie updateMovie(Long id, MovieRequest request) {

        Movie existingMovie = movieRepository.findById(id)
                .orElse(null);

        if (existingMovie == null) {
            return null;
        }

        String title = request.getTitle().trim();
        String genre = request.getGenre().trim();
        String language = request.getLanguage().trim();
        Integer duration = request.getDurationMinutes();

        boolean duplicateExists =
                movieRepository
                        .existsByTitleIgnoreCaseAndGenreIgnoreCaseAndLanguageIgnoreCaseAndDurationMinutesAndIdNot(
                                title,
                                genre,
                                language,
                                duration,
                                id
                        );

        if (duplicateExists) {
            throw new DuplicateMovieException(
                    "Another movie with the same title, genre, language, and duration already exists."
            );
        }

        existingMovie.setTitle(title);
        existingMovie.setGenre(genre);
        existingMovie.setLanguage(language);
        existingMovie.setDurationMinutes(duration);
        existingMovie.setRating(request.getRating());

        return movieRepository.save(existingMovie);
    }



    @Transactional
    public boolean deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            return false;
        }

        movieRepository.deleteById(id);
        return true;
    }
}
