package wishlist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import wishlist.dto.UserDTO;
import wishlist.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "created_at", target = "created_at")
    })
    UserDTO toDTO(User user);

    @InheritInverseConfiguration
    User toEntity(UserDTO dto);
}
