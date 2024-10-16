package wishlist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wishlist.service.GiftService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/gifts")
public class GiftController {
    private final GiftService giftService;

    @GetMapping("/")
    public ResponseEntity<?> getAllGifts() {
        return ResponseEntity.ok(giftService.getAllGifts());
    }
}
