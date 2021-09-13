package co.com.poli.servicebooking.repositories;

import co.com.poli.servicebooking.entities.Booking;
import co.com.poli.servicebooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    User  findByUserId(Long id);
}
