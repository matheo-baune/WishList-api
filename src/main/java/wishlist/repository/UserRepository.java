package wishlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wishlist.entity.User;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
