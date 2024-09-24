package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wishlist.model.Reservations;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
}
