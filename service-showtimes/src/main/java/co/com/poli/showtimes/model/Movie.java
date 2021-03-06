package co.com.poli.showtimes.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class Movie {

    private Long id;
    private String title;
    private String director;
    private Long rating;

}