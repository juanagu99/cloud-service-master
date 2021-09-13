package co.com.poli.servicebooking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ShowTime {

    private Long Id;
    private Date date;
    private List<ShowTimeMovie> listMovies;

}