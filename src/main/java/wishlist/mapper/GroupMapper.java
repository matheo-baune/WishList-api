package wishlist.mapper;

import org.mapstruct.Mapper;
import wishlist.dto.GroupDTO;
import wishlist.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDTO toDTO(Group group);
    Group toEntity(GroupDTO groupDTO);
}