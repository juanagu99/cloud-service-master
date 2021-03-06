package co.com.poli.showtimes.relation;

import co.com.poli.showtimes.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-movie",fallback = MovieFeignFallBackHystrix.class)
public interface MovieFeign {

    @GetMapping("/movie/{id}")
    Response findById(@PathVariable("id") Long id);

}
