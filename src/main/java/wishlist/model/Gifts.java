package wishlist.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "gifts")
public class Gifts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}
