package co.com.poli.servicebooking.relation;

import co.com.poli.servicebooking.model.ShowTime;
import co.com.poli.servicebooking.utils.Response;
import co.com.poli.servicebooking.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShowTimesFeignFallBackHystrix implements ShowTimeFeign {

    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        ShowTime showTime = ShowTime.builder()
                .Id(null)
                .date(null)
                .build();
        return  builder.success(showTime);
    }

}
