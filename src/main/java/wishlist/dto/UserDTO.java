package wishlist.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotNull
    private String username;

    private String email;
    private String role;
}
