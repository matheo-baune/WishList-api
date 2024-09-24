package wishlist.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservations {
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