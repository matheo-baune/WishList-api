package wishlist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import wishlist.dto.GroupDTO;
import wishlist.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "created_by", target = "created_by"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "created_at", target = "created_at")
    })
    GroupDTO toDTO(Group group);

    @InheritInverseConfiguration
    Group toEntity(GroupDTO groupDTO);
}