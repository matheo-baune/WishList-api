package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wishlist.model.Gifts;

public interface GiftRepository extends JpaRepository<Gifts, Long> {
}
