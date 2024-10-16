package wishlist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getGiftById(@PathVariable Long id) {
        return ResponseEntity.ok(giftService.getGiftById(id));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGiftById(@PathVariable Long id) {
        boolean isDeleted = giftService.deleteGiftById(id);
        return isDeleted ?
                ResponseEntity.ok("Gift deleted successfully") :
                ResponseEntity.internalServerError().body("Gift could not be deleted (not existed or wrong identifier)");
    }
}
