package co.com.poli.servicebooking.relation;

import co.com.poli.servicebooking.model.User;
import co.com.poli.servicebooking.utils.Response;
import co.com.poli.servicebooking.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeignFallBackHystrix implements UserFeign {

    private final ResponseBuilder builder;

    @Override
    public Response findById(Long id) {
        User user = User.builder()
                .Id(null)
                .name("")
                .lastname("").build();
        return  builder.success(user);
    }

}
