package wishlist.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private String imageUrl;

    @NotNull
    private Double price;
}
