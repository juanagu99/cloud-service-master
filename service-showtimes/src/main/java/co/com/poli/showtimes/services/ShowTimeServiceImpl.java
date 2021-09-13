package co.com.poli.showtimes.services;

import co.com.poli.showtimes.entities.ShowTime;
import co.com.poli.showtimes.entities.ShowTimeMovie;
import co.com.poli.showtimes.model.Movie;
import co.com.poli.showtimes.relation.MovieFeign;
import co.com.poli.showtimes.repositories.ShowTimeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieFeign movieFeign;

    @Override
    public void save(ShowTime showTime) {
        ModelMapper modelMapper = new ModelMapper();
        List<ShowTimeMovie> itemList = showTime.getListMovies().stream()
                .map( showTimeMovie -> {
                    Movie m = modelMapper.map(movieFeign.findById(showTimeMovie.getMovieId()).getData(),Movie.class);
                    showTimeMovie.setMovie(m);
                    return showTimeMovie;
                }).collect(Collectors.toList());

        showTime.setListMovies(itemList);
        showTimeRepository.save(showTime);

    }

    @Override
    public void delete(ShowTime showTime) {
      showTimeRepository.delete(showTime);
    }

    @Override
    public List<ShowTime> findAll() {
        List<ShowTime> showstimeList = showTimeRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        for (ShowTime showtimeItem: showstimeList) {
            for( ShowTimeMovie showTimeMovieItem : showtimeItem.getListMovies() ){
                Movie m = modelMapper.map(movieFeign.findById(showTimeMovieItem.getMovieId()).getData(),Movie.class);
                showTimeMovieItem.setMovie(m);
            }
        }
        return showstimeList;
    }

    @Override
    public ShowTime findById(Long id) {

        ShowTime showtime = showTimeRepository.findById(id).orElse(null);

        ModelMapper modelMapper = new ModelMapper();

        List<ShowTimeMovie> itemList = showtime.getListMovies().stream()
                .map( showTimeMovie -> {
                    Movie m = modelMapper.map(movieFeign.findById(showTimeMovie.getMovieId()).getData(),Movie.class);
                    showTimeMovie.setMovie(m);
                    return showTimeMovie;
                }).collect(Collectors.toList());

        showtime.setListMovies(itemList);

        return showtime;
    }
}
