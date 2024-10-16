package wishlist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wishlist.dto.GroupDTO;
import wishlist.dto.UserDTO;
import wishlist.service.UserService;

import java.util.List;

@Tag(name = "User", description = "Endpoint user")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    //TODO - Rajouter de l'administration pour bloquer certaines routes aux utilisateurs non-administrateurs
    @Operation(summary = "Create a new user")
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get a specific user")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get all groups of a user")
    @GetMapping("/{id}/groups")
    public ResponseEntity<List<GroupDTO>> getAllGroupOfUser(@PathVariable Long id) {
        List<GroupDTO> groups = userService.getAllGroupOfUser(id);
        return ResponseEntity.ok(groups);
    }



    @Operation(
            summary = "4. Update user",
            description = "This method allows you to update a user by providing the necessary details."
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(id, userDTO);
        return user != null ?
                ResponseEntity.ok().body(user) :
                ResponseEntity.internalServerError().body("User could not be updated (not existed or wrong identifier)");
    }



    @Operation(summary = "Supprimer un utilisateur")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ?
                ResponseEntity.ok().body("User deleted successfully") :
                ResponseEntity.internalServerError().body("User could not be deleted (not existed or wrong identifier)");
    }
}
