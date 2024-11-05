package wishlist.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wishlist.dto.GroupDTO;
import wishlist.dto.UserDTO;
import wishlist.service.GroupMembersService;
import wishlist.service.GroupService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GroupController {
    private final GroupService groupService;
    private final GroupMembersService groupMembersService;


    @GetMapping("/")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        GroupDTO group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UserDTO>> getAllUsersOfGroup(@PathVariable Long id) {
        List<UserDTO> users = groupService.getAllUsersOfGroup(id);
        return ResponseEntity.ok(users);
    }



    @PostMapping("/")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        GroupDTO createdGroup = groupService.createGroup(groupDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(groupDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdGroup);
    }

    @PostMapping("/{groupCode}/join/{userId}")
    public ResponseEntity<?> joinGroup(@PathVariable String groupCode, @PathVariable Long userId) {
        boolean isJoined = groupMembersService.joinGroup(groupCode, userId);
        return isJoined ?
                ResponseEntity.ok().body("User joined group successfully") :
                ResponseEntity.internalServerError().body("User could not join group (not existed or wrong identifier)");
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO) {
        GroupDTO updatedGroup = groupService.updateGroup(id, groupDTO);
        return updatedGroup != null ?
                ResponseEntity.ok().body(updatedGroup) :
                ResponseEntity.internalServerError().body("Group could not be updated (not existed or wrong identifier)");
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        boolean isDeleted = groupService.deleteGroup(id);
        return isDeleted ?
                ResponseEntity.ok("Group deleted successfully") :
                ResponseEntity.internalServerError().body("Group could not be deleted (not existed or wrong identifier)");
    }
}
