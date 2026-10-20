
package com.tixora.dto;

public class TheatreResponse {

    private Long id;
    private String name;
    private String city;
    private String address;
    private boolean active;

    public TheatreResponse() {
    }

    public TheatreResponse(
            Long id,
            String name,
            String city,
            String address,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
