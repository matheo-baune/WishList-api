package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wishlist.entity.Reservation;

public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Reservation r WHERE r.giftId = :id")
    boolean existsByGiftId(Long id);
}
