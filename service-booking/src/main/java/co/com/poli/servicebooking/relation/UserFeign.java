package co.com.poli.servicebooking.relation;

import co.com.poli.servicebooking.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-user",fallback = UserFeignFallBackHystrix.class)
public interface UserFeign {

    @GetMapping("/user/{id}")
    Response findById(@PathVariable("id") Long id);

}
