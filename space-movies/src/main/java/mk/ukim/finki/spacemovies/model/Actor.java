package mk.ukim.finki.spacemovies.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Actor model
 */
@Data
@Entity
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String countryOfOrigin;

    public Actor(String firstName, String lastName, Integer age, String countryOfOrigin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.countryOfOrigin = countryOfOrigin;
    }

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }
}
