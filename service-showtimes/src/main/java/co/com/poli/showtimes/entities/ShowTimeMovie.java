package co.com.poli.showtimes.entities;

import co.com.poli.showtimes.model.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="showtimemovie")
public class ShowTimeMovie {
//tabla que me relaciona los horarios por pelicula

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId;

    //no se persiste
    @Transient
    private Movie movie;

}
