package mk.ukim.finki.spacemovies.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.spacemovies.model.enumerations.InterestListEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * InterestList model
 */
@Data
@Entity
@NoArgsConstructor
public class InterestList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private InterestListEnum status;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Movie> movies;

    public InterestList(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.status = InterestListEnum.CREATED;
        this.movies = new ArrayList<>();
    }
}
