package co.com.poli.showtimes.repositories;

import co.com.poli.showtimes.entities.ShowTimeMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeMovieRepository extends JpaRepository<ShowTimeMovie,Long> {

}
