package wishlist.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gift_id", nullable = false)
    private Long giftId;

    @Column(name = "reserved_by", nullable = false)
    private Integer reservedBy;

    @Column(name = "reserved_at", nullable = false)
    private Date reservedAt;
}