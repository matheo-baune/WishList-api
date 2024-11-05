package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wishlist.entity.Gift;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    @Query("SELECT g FROM Gift g WHERE g.created_by = :id AND g.group.id = :groupID")
    List<Gift> findGiftsByUserIdAndGroupId(Long id, Long groupID);
}
