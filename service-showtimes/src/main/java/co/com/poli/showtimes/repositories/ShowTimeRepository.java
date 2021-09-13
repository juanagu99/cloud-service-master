package co.com.poli.showtimes.repositories;

import co.com.poli.showtimes.entities.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime,Long> {

}
