package co.com.poli.movie.controller;

import co.com.poli.movie.entities.User;
import co.com.poli.movie.services.UserService;
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
@RequestMapping(value="/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()){
            return builder.failed(this.formatMessage((result)));
        }
        userService.save(user);
        return builder.success(user);
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return builder.success(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
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
