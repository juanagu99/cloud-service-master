package co.com.poli.movie.controller;

import co.com.poli.movie.entities.Movie;
import co.com.poli.movie.services.MovieService;
import co.com.poli.movie.utils.ErrorMessage;
import co.com.poli.movie.utils.Response;
import co.com.poli.movie.utils.ResponseBuilder;
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
@RequestMapping(value="/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody Movie movie, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage((result)));
        }
        movieService.save(movie);
        return builder.success(movie);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        Movie movie = movieService.findById(id);
        return builder.success(movie);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll(){
        List<Movie> movies = movieService.findAll();
        if(movies.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movies);
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
