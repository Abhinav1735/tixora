
package com.tixora.repository;

import com.tixora.entity.Screen;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScreenRepository extends JpaRepository<Screen, Long> {

    @Override
    @EntityGraph(attributePaths = "theatre")
    List<Screen> findAll();

    @Override
    @EntityGraph(attributePaths = "theatre")
    Optional<Screen> findById(Long id);

    @EntityGraph(attributePaths = "theatre")
    List<Screen> findByTheatreId(Long theatreId);

    @EntityGraph(attributePaths = "theatre")
    List<Screen> findByTheatreIdAndActiveTrue(Long theatreId);

    boolean existsByTheatreIdAndNameIgnoreCase(
            Long theatreId,
            String name
    );

    boolean existsByTheatreIdAndNameIgnoreCaseAndIdNot(
            Long theatreId,
            String name,
            Long id
    );
}
