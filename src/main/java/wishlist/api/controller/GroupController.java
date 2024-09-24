package wishlist.api.controller;

import org.springframework.web.bind.annotation.*;
import wishlist.model.Group;
import wishlist.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // GET MAPPING
    @GetMapping("/groups")
    public String getGroupsForUser(@RequestParam int idUser) {
        return "This is a string response " + idUser;
    }

    @GetMapping("/all-groups")
    @ResponseBody
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/group/{id}")
    @ResponseBody
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    // POST MAPPING

    // DELETE MAPPING

}
