
package com.tixora.dto;

public class ScreenResponse {

    private Long id;
    private String name;
    private Long theatreId;
    private String theatreName;
    private Integer totalSeats;
    private boolean active;

    public ScreenResponse() {
    }

    public ScreenResponse(
            Long id,
            String name,
            Long theatreId,
            String theatreName,
            Integer totalSeats,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.totalSeats = totalSeats;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
