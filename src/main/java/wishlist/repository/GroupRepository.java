package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wishlist.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
