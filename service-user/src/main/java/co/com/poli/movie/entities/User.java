package co.com.poli.movie.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty(message = "El nombre no puede ser vacio")
    @Column(name="lastname",nullable = false)
    private String lastname;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(Id, user.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
