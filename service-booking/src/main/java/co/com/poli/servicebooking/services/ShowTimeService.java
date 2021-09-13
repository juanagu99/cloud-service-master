package co.com.poli.servicebooking.services;

import co.com.poli.servicebooking.entities.Booking;

import java.util.List;

public interface ShowTimeService {

    void save(Booking booking);
    void delete(Booking booking);
    List<Booking> findAll();
    Booking findById(Long id);

}
