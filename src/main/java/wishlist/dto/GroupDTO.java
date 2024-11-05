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

    @Size(min = 1, max = 50)
    private String description;

    @NotNull
    private Integer created_by;

    @NotNull
    @Size(min = 1, max = 10)
    private String code;

    @NotNull
    private String created_at;
}