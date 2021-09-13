package co.com.poli.movie.services;

import co.com.poli.movie.entities.Movie;
import co.com.poli.movie.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

}
