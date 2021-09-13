package co.com.poli.servicebooking.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class ShowTimeMovie {

    private Long Id;
    private Date date;
    private List<ShowTimeMovie> listMovies;

}