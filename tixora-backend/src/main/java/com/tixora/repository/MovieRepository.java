
package com.tixora.repository;

import com.tixora.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByTitleIgnoreCaseAndGenreIgnoreCaseAndLanguageIgnoreCaseAndDurationMinutes(
            String title,
            String genre,
            String language,
            Integer durationMinutes
    );

    boolean existsByTitleIgnoreCaseAndGenreIgnoreCaseAndLanguageIgnoreCaseAndDurationMinutesAndIdNot(
            String title,
            String genre,
            String language,
            Integer durationMinutes,
            Long id
    );
}
