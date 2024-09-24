package com.baune.wishlist.api.controller;

import com.baune.wishlist.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    // POST MAPPING

    // DELETE MAPPING

}
