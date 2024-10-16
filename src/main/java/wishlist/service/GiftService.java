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
}
