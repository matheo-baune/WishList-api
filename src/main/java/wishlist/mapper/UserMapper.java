package wishlist.mapper;

import org.mapstruct.Mapper;
import wishlist.dto.UserDTO;
import wishlist.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User entity);
    User toEntity(UserDTO dto);
}
