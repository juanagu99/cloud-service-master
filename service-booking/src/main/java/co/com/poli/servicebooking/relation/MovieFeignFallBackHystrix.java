package co.com.poli.servicebooking.relation;

import co.com.poli.servicebooking.model.Movie;
import co.com.poli.servicebooking.utils.Response;
import co.com.poli.servicebooking.utils.ResponseBuilder;
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
