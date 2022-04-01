package mk.ukim.finki.spacemovies.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.spacemovies.model.enumerations.LanguageEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Movie model
 */
@Data
@Entity
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    private Integer duration;

    private LocalDate releaseDate;

    private Float price;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @ManyToOne
    private Genre genre;

    @ManyToMany
    private List<Actor> actors;

    @ManyToMany
    private List<MovieTheatre> theatres;

    public Movie(String title, Integer duration, LocalDate releaseDate, Float price, String description, LanguageEnum language, Genre genre, List<Actor> actors, List<MovieTheatre> theatres) {
        this.title = title;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.price = price;
        this.description = description;
        this.language = language;
        this.genre = genre;
        this.actors = actors;
        this.theatres = theatres;
    }
}
