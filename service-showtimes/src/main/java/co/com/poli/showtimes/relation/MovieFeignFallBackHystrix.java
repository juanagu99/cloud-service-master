package co.com.poli.showtimes.relation;

import co.com.poli.showtimes.model.Movie;
import co.com.poli.showtimes.utils.Response;
import co.com.poli.showtimes.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieFeignFallBackHystrix implements MovieFeign {

    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        Movie movie = Movie.builder()
                .id(null)
                .title("")
                .director("").build();
        return  builder.success(movie);
    }

}
