package wishlist.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import wishlist.dto.GiftDTO;
import wishlist.entity.Gift;

@Mapper(componentModel = "spring")
public interface GiftMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "link", target = "link"),
            @Mapping(source = "image_url", target = "image_url"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "created_by", target = "created_by"),
            @Mapping(source = "group_id", target = "group_id")
    })
    GiftDTO toDTO(Gift gift);

    @InheritInverseConfiguration
    Gift toEntity(GiftDTO giftDTO);
}
