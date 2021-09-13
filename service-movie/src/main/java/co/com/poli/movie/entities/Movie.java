package co.com.poli.movie.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name = "title",nullable = false)
    private String title;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name="director",nullable = false)
    private String director;


    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name="rating",nullable = false)
    @Min(1)
    @Max(5)
    private Long rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
