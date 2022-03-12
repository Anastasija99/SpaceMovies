package mk.ukim.finki.spacemovies.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Movie theatre model
 */
@Data
@Entity
@NoArgsConstructor
public class MovieTheatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String address;

    private Integer movieTheatreHalls;

    public MovieTheatre(String name, String city, String address, Integer movieTheatreHalls) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.movieTheatreHalls = movieTheatreHalls;
    }
}