package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wishlist.model.GroupMembers;

public interface GroupMembersRepository extends JpaRepository<GroupMembers, Long> {
}
