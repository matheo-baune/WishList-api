package wishlist.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.Utils;
import wishlist.dto.GiftDTO;
import wishlist.entity.Gift;
import wishlist.mapper.GiftMapper;
import wishlist.repository.GiftRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;
    private final GiftMapper giftMapper;

    public List<GiftDTO> getAllGifts() {
        return giftRepository.findAll().stream()
                .map(giftMapper::toDTO)
                .toList();
    }

    public GiftDTO getGiftById(Long id) {
        return giftRepository.findById(id)
                .map(giftMapper::toDTO)
                .orElse(null);
    }

    public GiftDTO createGift(@Valid GiftDTO giftDTO) {
        Gift gift = giftMapper.toEntity(giftDTO);
        Gift savedGift = giftRepository.save(gift);
        return giftMapper.toDTO(savedGift);
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
