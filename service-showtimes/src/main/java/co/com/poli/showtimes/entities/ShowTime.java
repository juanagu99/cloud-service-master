package co.com.poli.showtimes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "showtime")
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "date")
    private Date date;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="showtime_Id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private List<ShowTimeMovie> listMovies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowTime showTime = (ShowTime) o;
        return Objects.equals(Id, showTime.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
