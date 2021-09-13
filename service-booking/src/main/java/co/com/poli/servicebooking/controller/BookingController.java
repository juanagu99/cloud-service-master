package co.com.poli.servicebooking.controller;

import co.com.poli.servicebooking.entities.Booking;
import co.com.poli.servicebooking.services.ShowTimeServiceImpl;
import co.com.poli.servicebooking.utils.ErrorMessage;
import co.com.poli.servicebooking.utils.Response;
import co.com.poli.servicebooking.utils.ResponseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final ShowTimeServiceImpl showtimeService;
    private final ResponseBuilder builder;

    @PostMapping()
    public Response save(@Valid @RequestBody Booking booking, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(formatMessage(result));
        }
        showtimeService.save(booking);
        return builder.success(booking);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> findAll(){
        List<Booking> booking = showtimeService.findAll();
        if(booking.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Booking booking = showtimeService.findById(id);
        if (booking == null){
            return builder.failed(booking);
        }else{
            return builder.success(booking);
        }

    }

    @GetMapping("/user/{id}")
    public Response findByUserId(@PathVariable("id") Long id){
        Booking booking = showtimeService.findByUserId(id);
        if (booking == null){
            return builder.failed(booking);
        }else{
            return builder.success(booking);
        }

    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Booking booking = showtimeService.findById(id);
        if (booking == null){
            return builder.failed(booking);
        }else {
            showtimeService.delete(booking);
            return builder.success(booking);
        }
    }


    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try{
            json = objectMapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }

}
