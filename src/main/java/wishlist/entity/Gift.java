package wishlist.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Schema(description = "Gift object")
@Data
@Entity
@Table(name = "gifts")
public class Gift {
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
