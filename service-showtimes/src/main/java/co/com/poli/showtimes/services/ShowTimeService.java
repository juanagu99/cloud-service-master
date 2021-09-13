package co.com.poli.showtimes.services;

import co.com.poli.showtimes.entities.ShowTime;

import java.util.List;

public interface ShowTimeService {

    void save(ShowTime showTime);
    void delete(ShowTime showTime);
    List<ShowTime> findAll();
    ShowTime findById(Long id);


}
