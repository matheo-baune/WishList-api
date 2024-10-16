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
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "price", target = "price")
    })
    GiftDTO toDTO(Gift gift);

    @InheritInverseConfiguration
    Gift toEntity(GiftDTO giftDTO);
}
