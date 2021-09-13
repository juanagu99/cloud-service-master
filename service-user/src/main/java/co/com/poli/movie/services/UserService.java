package co.com.poli.movie.services;

import co.com.poli.movie.entities.User;

import java.util.List;

public interface UserService {

    void save(User user);
    void delete(User user);
    List<User> findAll();
    User findById(Long id);
}
