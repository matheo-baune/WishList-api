package wishlist.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class GroupDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private Integer createdBy;

    @NotNull
    @Size(min = 1, max = 10)
    private String code;

    @NotNull
    private String createdAt;
}