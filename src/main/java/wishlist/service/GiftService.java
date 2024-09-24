package wishlist.service;

import org.springframework.stereotype.Service;
import wishlist.repository.GiftRepository;

@Service
public class GiftService {
    private final GiftRepository giftRepository;

    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

}
