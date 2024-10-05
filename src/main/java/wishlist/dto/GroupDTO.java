package wishlist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
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
}