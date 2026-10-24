
package com.tixora.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ScreenRequest {

    @NotBlank(message = "Screen name is required")
    @Size(max = 50, message = "Screen name cannot exceed 50 characters")
    private String name;

    @NotNull(message = "Theatre ID is required")
    @Positive(message = "Theatre ID must be positive")
    private Long theatreId;

    @NotNull(message = "Total seats is required")
    @Positive(message = "Total seats must be positive")
    @Min(value = 1, message = "A screen must have at least one seat")
    @Max(value = 1000, message = "Total seats cannot exceed 1000")
    private Integer totalSeats;

    public ScreenRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }
}
