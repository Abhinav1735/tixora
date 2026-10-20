
package com.tixora.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;

@Entity
@Table(
        name = "movie",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_movie_details",
                        columnNames = {
                                "title",
                                "genre",
                                "language",
                                "duration_minutes"
                        }
                )
        }
)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Movie title is required")
    private String title;

    @NotBlank(message = "Movie genre is required")
    private String genre;

    @NotBlank(message = "Movie language is required")
    private String language;

    @NotNull(message = "Movie duration is required")
    @Positive(message = "Movie duration must be greater than zero")
    @Max(value = 600, message = "Movie duration cannot exceed 600 minutes")
    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;

    @NotNull(message = "Movie rating is required")
    @DecimalMin(value = "0.0", message = "Rating cannot be below 0")
    @DecimalMax(value = "10.0", message = "Rating cannot exceed 10")
    private Double rating;

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
