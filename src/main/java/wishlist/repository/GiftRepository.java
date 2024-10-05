package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wishlist.entity.Gift;

public interface GiftRepository extends JpaRepository<Gift, Long> {
}
