package wishlist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Schema(description = "Reservation object")
@Data
@Entity
@Table(name = "reservations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"gift_id"})
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gift_id", nullable = false)
    private Long giftId;

    @Column(name = "reserved_by", nullable = false)
    private Long reserved_by;

    @Column(name = "reserved_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reserved_at;

    @PrePersist
    protected void onCreate() {
        reserved_at = new Date();
    }
}