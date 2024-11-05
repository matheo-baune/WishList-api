package wishlist.dto;

import lombok.Data;
import wishlist.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class GiftDTO {
    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    @Size(max = 255)
    private String link;

    @Size(max = 255)
    private String image_url;

    @NotNull
    private Double price;

    @NotNull
    private Long group_id;

    @NotNull
    private Long created_by;


    @NotNull
    private Date created_at;

    private boolean is_reserved;
}
