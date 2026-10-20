
package com.tixora.dto;

public class MovieResponse {

    private Long id;
    private String title;
    private String genre;
    private String language;
    private Integer durationMinutes;
    private Double rating;

    public MovieResponse() {
    }

    public MovieResponse(
            Long id,
            String title,
            String genre,
            String language,
            Integer durationMinutes,
            Double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
