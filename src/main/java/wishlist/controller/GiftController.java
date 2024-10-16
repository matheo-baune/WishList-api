package wishlist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wishlist.dto.GiftDTO;
import wishlist.service.GiftService;

import java.net.URI;

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


    @PostMapping("/")
    public ResponseEntity<?> createGift(@RequestBody GiftDTO giftDTO) {
        GiftDTO giftCreated = giftService.createGift(giftDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(giftDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(giftCreated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGiftById(@PathVariable Long id) {
        boolean isDeleted = giftService.deleteGiftById(id);
        return isDeleted ?
                ResponseEntity.ok("Gift deleted successfully") :
                ResponseEntity.internalServerError().body("Gift could not be deleted (not existed or wrong identifier)");
    }
}
