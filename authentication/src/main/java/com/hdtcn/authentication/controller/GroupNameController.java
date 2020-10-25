package com.hdtcn.authentication.controller;

import com.hdtcn.authentication.entity.GroupName;
import com.hdtcn.authentication.service.GroupNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/groups")
public class GroupNameController {
    @Autowired
    private GroupNameService groupNameService;
    public ResponseEntity<?> getListGroup() {
        List<GroupName> groups = groupNameService.getGroupNames();
        return ResponseEntity.ok(groups);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable int id) {
        GroupName group = groupNameService.getGroupNameById(id);
        return ResponseEntity.ok(group);
    }
}
