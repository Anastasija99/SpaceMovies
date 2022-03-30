package mk.ukim.finki.spacemovies.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieTheatre that = (MovieTheatre) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public MovieTheatre(String name, String city, String address, Integer movieTheatreHalls) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.movieTheatreHalls = movieTheatreHalls;
    }
}