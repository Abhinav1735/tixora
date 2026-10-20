
package com.tixora.dto;

import com.tixora.entity.Theatre;

public final class TheatreMapper {

    private TheatreMapper() {
        // Utility class; prevent object creation.
    }

    public static TheatreResponse toResponse(Theatre theatre) {
        if (theatre == null) {
            return null;
        }

        return new TheatreResponse(
                theatre.getId(),
                theatre.getName(),
                theatre.getCity(),
                theatre.getAddress(),
                theatre.isActive()
        );
    }
}
