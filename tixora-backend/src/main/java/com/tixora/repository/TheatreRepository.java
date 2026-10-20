
package com.tixora.repository;

import com.tixora.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    List<Theatre> findByCityIgnoreCase(String city);

    boolean existsByNameIgnoreCaseAndCityIgnoreCase(
            String name,
            String city
    );
}
