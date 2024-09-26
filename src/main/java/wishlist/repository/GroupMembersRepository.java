package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wishlist.entity.GroupMember;

import java.util.Collection;

public interface GroupMembersRepository extends JpaRepository<GroupMember, Long> {
}
