package wishlist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.dto.GiftDTO;
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

    public boolean deleteGiftById(Long id) {
        if (giftRepository.existsById(id)) {
            giftRepository.deleteById(id);
            return !giftRepository.existsById(id);
        }
        return false;
    }
}
