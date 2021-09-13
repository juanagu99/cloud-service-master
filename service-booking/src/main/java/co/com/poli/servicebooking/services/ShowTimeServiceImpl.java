package co.com.poli.servicebooking.services;

import co.com.poli.servicebooking.entities.Booking;
import co.com.poli.servicebooking.model.Movie;
import co.com.poli.servicebooking.model.ShowTime;
import co.com.poli.servicebooking.model.User;
import co.com.poli.servicebooking.relation.MovieFeign;
import co.com.poli.servicebooking.relation.ShowTimeFeign;
import co.com.poli.servicebooking.relation.UserFeign;
import co.com.poli.servicebooking.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final BookingRepository bookingRepository;
    private final UserFeign userFeign;
    private final ShowTimeFeign showTimeFeign;

    @Override
    public void save(Booking booking) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userFeign.findById(booking.getUserId()),User.class);
        ShowTime showTime = modelMapper.map(showTimeFeign.findById(booking.getShowtimeId()),ShowTime.class);
        if (user != null && showTime != null ) {
            bookingRepository.save(booking);
        }
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookingList = bookingRepository.findAll();
        for( Booking booking : bookingList){
            ModelMapper modelMapper = new ModelMapper();

            User user = modelMapper.map(userFeign.findById(booking.getUserId()),User.class);
            ShowTime showTime = modelMapper.map(showTimeFeign.findById(booking.getShowtimeId()),ShowTime.class);

            if (user != null && showTime != null ) {
                booking.setUser(user);
                booking.setShowtime(showTime);
            }

        }
        return bookingList;
    }

    @Override
    public Booking findById(Long id) {

        Booking booking = bookingRepository.findById(id).orElse(null);
        ModelMapper modelMapper = new ModelMapper();

        User user = modelMapper.map(userFeign.findById(booking.getUserId()),User.class);
        ShowTime showTime = modelMapper.map(showTimeFeign.findById(booking.getShowtimeId()),ShowTime.class);

        if (user != null && showTime != null ){
            booking.setUser(user);
            booking.setShowtime(showTime);
            return booking;
        }else{
            return null;
        }
    }
}
