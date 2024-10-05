package wishlist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wishlist.repository.GiftRepository;

@Service
@RequiredArgsConstructor
public class GiftService {
    private final GiftRepository giftRepository;

}
