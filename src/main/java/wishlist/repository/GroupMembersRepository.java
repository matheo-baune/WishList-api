package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import wishlist.entity.Group;
import wishlist.entity.GroupMember;
import wishlist.entity.User;

import java.util.List;

public interface GroupMembersRepository extends JpaRepository<GroupMember, Long> {
    @Query("SELECT gm.group FROM GroupMember gm WHERE gm.user.id = :userId")
    List<Group> findGroupsByUserId(Long userId);

    @Query("SELECT gm.user FROM GroupMember gm WHERE gm.group.id = :id")
    List<User> findUsersByGroupId(Long id);
}
