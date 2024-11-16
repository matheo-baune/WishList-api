package wishlist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import wishlist.Utils;

import java.util.Date;
import java.util.Set;

@Schema(description = "User object")
@Data
@Entity
@Table(name = "users")
public class User {
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false, length = 32)
    private String salt;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @OneToMany(mappedBy = "user")
    private Set<GroupMember> groupMembers;

    public User() {

    }


    @PrePersist
    protected void onCreate() {
        created_at = new Date();
    }
}
