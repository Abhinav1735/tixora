
package com.tixora.service;

import com.tixora.dto.TheatreRequest;
import com.tixora.entity.Theatre;
import com.tixora.exception.DuplicateTheatreException;
import com.tixora.repository.TheatreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public List<Theatre> getTheatresByCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is required.");
        }

        return theatreRepository.findByCityIgnoreCase(city.trim());
    }

    public Theatre getTheatreById(Long id) {
        return theatreRepository.findById(id).orElse(null);
    }

    @Transactional
    public Theatre createTheatre(TheatreRequest request) {
        String name = request.getName().trim();
        String city = request.getCity().trim();
        String address = request.getAddress().trim();

        if (theatreRepository.existsByNameIgnoreCaseAndCityIgnoreCase(name, city)) {
            throw new DuplicateTheatreException(
                    "A theatre with the same name in this city already exists."
            );
        }

        Theatre theatre = new Theatre();
        theatre.setName(name);
        theatre.setCity(city);
        theatre.setAddress(address);
        theatre.setActive(true);

        return theatreRepository.save(theatre);
    }

    @Transactional
    public Theatre updateTheatre(Long id, TheatreRequest request) {
        Theatre existingTheatre = theatreRepository.findById(id).orElse(null);

        if (existingTheatre == null) {
            return null;
        }

        String name = request.getName().trim();
        String city = request.getCity().trim();
        String address = request.getAddress().trim();

        boolean duplicateExists =
                theatreRepository.existsByNameIgnoreCaseAndCityIgnoreCase(name, city)
                        && (
                        !existingTheatre.getName().equalsIgnoreCase(name)
                                || !existingTheatre.getCity().equalsIgnoreCase(city)
                );

        if (duplicateExists) {
            throw new DuplicateTheatreException(
                    "Another theatre with the same name in this city already exists."
            );
        }

        existingTheatre.setName(name);
        existingTheatre.setCity(city);
        existingTheatre.setAddress(address);

        return theatreRepository.save(existingTheatre);
    }

    @Transactional
    public boolean deleteTheatre(Long id) {
        Theatre theatre = theatreRepository.findById(id).orElse(null);

        if (theatre == null) {
            return false;
        }

        theatreRepository.delete(theatre);
        return true;
    }
}
