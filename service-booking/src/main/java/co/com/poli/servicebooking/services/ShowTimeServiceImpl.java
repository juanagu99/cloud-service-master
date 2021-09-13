package co.com.poli.servicebooking.services;

import co.com.poli.servicebooking.entities.Booking;
import co.com.poli.servicebooking.model.ShowTime;
import co.com.poli.servicebooking.model.User;
import co.com.poli.servicebooking.relation.ShowTimeFeign;
import co.com.poli.servicebooking.relation.UserFeign;
import co.com.poli.servicebooking.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final BookingRepository bookingRepository;
    private final UserFeign userFeign;
    private final ShowTimeFeign showTimeFeign;

    @Override
    public void save(Booking booking) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userFeign.findById(booking.getUserId()).getData(),User.class);
        ShowTime showTime = modelMapper.map(showTimeFeign.findById(booking.getShowtimeId()).getData(),ShowTime.class);
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

            User user = modelMapper.map(userFeign.findById(booking.getUserId()).getData(),User.class);
            ShowTime showTime = modelMapper.map(showTimeFeign.findById(booking.getShowtimeId()).getData(),ShowTime.class);

            if (user != null && showTime != null ) {
                booking.setUserId(user.getId());
                booking.setShowtimeId(showTime.getId());
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

        User user = modelMapper.map(userFeign.findById(booking.getUserId()).getData(),User.class);
        ShowTime showTime = modelMapper.map(showTimeFeign.findById(booking.getShowtimeId()).getData(),ShowTime.class);

        if (user != null && showTime != null ){
            booking.setUser(user);
            booking.setShowtime(showTime);
            return booking;
        }else{
            return null;
        }
    }

    @Override
    public Booking findByUserId(Long id) {

        ModelMapper modelMapper = new ModelMapper();

        User user = modelMapper.map(userFeign.findById(id).getData(),User.class);

        Optional<Booking> op = bookingRepository.findAll().stream().filter(b -> b.getId() == id).findFirst();

        System.out.println(op.get().getUserId());

        ShowTime showTime = modelMapper.map(showTimeFeign.findById( op.get().getId() ).getData(),ShowTime.class);

        if (user != null && showTime != null && op != null){
            Booking booking = op.get();
            booking.setUser(user);
            booking.setShowtime(showTime);
            return booking;
        }else{
            return null;
        }
    }
}
