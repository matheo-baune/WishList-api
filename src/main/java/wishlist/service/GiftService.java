package wishlist.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.Utils;
import wishlist.dto.GiftDTO;
import wishlist.entity.Gift;
import wishlist.entity.Group;
import wishlist.mapper.GiftMapper;
import wishlist.repository.GiftRepository;
import wishlist.repository.GroupRepository;
import wishlist.repository.ReservationsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {
    private final ReservationsRepository reservationsRepository;
    private final GroupRepository groupRepository;
    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;

    public List<GiftDTO> getAllGifts() {
        return giftRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private GiftDTO convertToDTO(Gift gift){
        GiftDTO giftDTO = giftMapper.toDTO(gift);
        boolean isReserved = reservationsRepository.existsByGiftId(gift.getId());
        giftDTO.set_reserved(isReserved);
        return giftDTO;
    }

    public GiftDTO getGiftById(Long id) {
        return giftRepository.findById(id)
                .map(giftMapper::toDTO)
                .orElse(null);
    }

    public GiftDTO createGift(@Valid GiftDTO giftDTO) {
        Gift gift = giftMapper.toEntity(giftDTO);
        Gift giftcreated = giftRepository.save(gift);
        return giftMapper.toDTO(giftcreated);
    }

    public GiftDTO updateGift(Long id, GiftDTO giftDTO) {
        Gift gift = giftRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gift not found"));

        Utils.updateEntityFromDTO(giftDTO, gift);

        Gift updatedgift = giftRepository.save(gift);
        return giftMapper.toDTO(updatedgift);
    }

    public boolean deleteGiftById(Long id) {
        if (giftRepository.existsById(id)) {
            giftRepository.deleteById(id);
            return !giftRepository.existsById(id);
        }
        return false;
    }

}
