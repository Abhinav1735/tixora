
package com.tixora.dto;

import com.tixora.entity.Screen;
import com.tixora.entity.Theatre;

public final class ScreenMapper {

    private ScreenMapper() {
        // Utility class; prevent object creation.
    }

    public static ScreenResponse toResponse(Screen screen) {
        if (screen == null) {
            return null;
        }

        Theatre theatre = screen.getTheatre();

        return new ScreenResponse(
                screen.getId(),
                screen.getName(),
                theatre.getId(),
                theatre.getName(),
                screen.getTotalSeats(),
                screen.isActive()
        );
    }
}
