package wishlist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    //GET ENDPOINT
    @Operation(summary = "Create a new user")
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Get a specific user")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get all groups of a user")
    @GetMapping("/{id}/groups")
    public ResponseEntity<List<GroupDTO>> getAllGroupOfUser(@PathVariable Long id) {
        List<GroupDTO> groups = userService.getAllGroupOfUser(id);
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @Operation(
            summary = "4. Update user",
            description = "This method allows you to update a user by providing the necessary details."
    )
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @Operation(summary = "Supprimer un utilisateur")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
