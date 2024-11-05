package wishlist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Schema(description = "GroupMember object")
@Data
@Entity
@Table(name = "group_members",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"group_id", "user_id"})
        }
)
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "joined_at", nullable = false)
    private Date joined_at;

    @PrePersist
    protected void onCreate() {
        joined_at = new Date();
    }
}