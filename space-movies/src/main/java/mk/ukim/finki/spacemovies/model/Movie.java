package mk.ukim.finki.spacemovies.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.spacemovies.model.enumerations.LanguageEnum;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    private Integer duration;

    private LocalDate releaseDate;

    private Float price;

    @Enumerated(EnumType.STRING)
    private LanguageEnum language;

    @ManyToOne
    private Genre genre;

    @ManyToMany
    private List<Actor> actors;

    @ManyToMany
    private List<MovieTheatre> theatres;
}
