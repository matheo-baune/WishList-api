package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wishlist.entity.Reservation;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
}
