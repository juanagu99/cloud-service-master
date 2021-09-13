package co.com.poli.servicebooking.entities;

import co.com.poli.servicebooking.model.ShowTime;
import co.com.poli.servicebooking.model.User;
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
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "showtimeId")
    private Long showtimeId;

    //no se persiste
    @Transient
    private User user;

    //no se persiste
    @Transient
    private ShowTime showtime;

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(Id, booking.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
}
