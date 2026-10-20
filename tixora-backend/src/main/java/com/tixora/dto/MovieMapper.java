
package com.tixora.dto;

import com.tixora.entity.Movie;

public final class MovieMapper {

    private MovieMapper() {
        // Utility class; no instances required.
    }

    public static MovieResponse toResponse(Movie movie) {
        if (movie == null) {
            return null;
        }

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getLanguage(),
                movie.getDurationMinutes(),
                movie.getRating()
        );
    }
}
