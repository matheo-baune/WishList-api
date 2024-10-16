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
            @Mapping(source = "createdBy", target = "createdBy"),
            @Mapping(source = "code", target = "code"),
            @Mapping(source = "createdAt", target = "createdAt")
    })
    GroupDTO toDTO(Group group);

    @InheritInverseConfiguration
    Group toEntity(GroupDTO groupDTO);
}