
package com.tixora.service;

import com.tixora.dto.ScreenRequest;
import com.tixora.entity.Screen;
import com.tixora.entity.Theatre;
import com.tixora.exception.DuplicateScreenException;
import com.tixora.repository.ScreenRepository;
import com.tixora.repository.TheatreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tixora.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    public ScreenService(
            ScreenRepository screenRepository,
            TheatreRepository theatreRepository
    ) {
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
    }

    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }

    public List<Screen> getScreensByTheatre(Long theatreId) {
        if (!theatreRepository.existsById(theatreId)) {
            throw new IllegalArgumentException(
                    "Theatre with ID " + theatreId + " does not exist."
            );
        }

        return screenRepository.findByTheatreId(theatreId);
    }

    public Screen getScreenById(Long id) {
        return screenRepository.findById(id).orElse(null);
    }

    @Transactional
    public Screen createScreen(ScreenRequest request) {
        Long theatreId = request.getTheatreId();

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Theatre with ID " + theatreId + " does not exist."
                ));

        String name = request.getName().trim();

        if (screenRepository.existsByTheatreIdAndNameIgnoreCase(theatreId, name)) {
            throw new DuplicateScreenException(
                    "A screen with the same name already exists in this theatre."
            );
        }

        Screen screen = new Screen();
        screen.setName(name);
        screen.setTheatre(theatre);
        screen.setTotalSeats(request.getTotalSeats());
        screen.setActive(true);

        return screenRepository.save(screen);
    }

    @Transactional
    public Screen updateScreen(Long id, ScreenRequest request) {
        Screen existingScreen = screenRepository.findById(id).orElse(null);

        if (existingScreen == null) {
            return null;
        }

        Long theatreId = request.getTheatreId();

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Theatre with ID " + theatreId + " does not exist."
                ));

        String name = request.getName().trim();

        boolean duplicateExists =
                screenRepository.existsByTheatreIdAndNameIgnoreCaseAndIdNot(
                        theatreId,
                        name,
                        id
                );

        if (duplicateExists) {
            throw new DuplicateScreenException(
                    "Another screen with the same name already exists in this theatre."
            );
        }

        existingScreen.setName(name);
        existingScreen.setTheatre(theatre);
        existingScreen.setTotalSeats(request.getTotalSeats());

        return screenRepository.save(existingScreen);
    }

    @Transactional
    public boolean deleteScreen(Long id) {
        Screen screen = screenRepository.findById(id).orElse(null);

        if (screen == null) {
            return false;
        }

        screenRepository.delete(screen);
        return true;
    }
}
