package com.hdtcn.authentication.controller;

import com.hdtcn.authentication.entity.UserRole;
import com.hdtcn.authentication.security.UserDetailIml;
import com.hdtcn.authentication.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRoleController {
    @Autowired
    UserRoleService userRoleService;
    @GetMapping("/isAdmin/{userID}/{groupID}")
    public ResponseEntity<?> checkAdminForGroup(@PathVariable Integer userID, @PathVariable Integer groupID) {
        System.out.println(userRoleService.checkRoleAdminForGroup(userID,groupID));
        if (userRoleService.checkRoleAdminForGroup(userID,groupID) != null) {
            return new ResponseEntity<>("You are the administrator of this group.", HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not the administrator of this group",HttpStatus.NOT_FOUND);
    }
    @PostMapping("/user-group")
    public ResponseEntity<?> addUserToGroup(@RequestBody UserRole userRole) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userID = ((UserDetailIml)principal).getUser().getId();
        if (!userRoleService.checkUserExists(userRole.getUser_id(),userRole.getGroup_id(),userRole.getRole_id())) {
            if (userRoleService.checkRoleAdminForGroup(userID,userRole.getGroup_id()) != null) {
                if (userRole.getRole_id() == 1) {
                    return  new ResponseEntity<>("The role is not suitable",HttpStatus.FORBIDDEN);
                } else {
                    if (userRoleService.addUserRole(userRole) != null) {
                        return new ResponseEntity<>("Successfully added members",HttpStatus.OK);
                    }
                }
            } else {
                return new ResponseEntity<>("You are not authorized.",HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("user already exists in the group",HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Member added was unsuccessful",HttpStatus.CONFLICT);

    }
    @PostMapping("/set-admin")
    public ResponseEntity<?> setRoleAdmin(@RequestBody UserRole userRole) {
        // System.out.println(userRole.getUser_id() +" "+userRole.getGroup_id());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userID = ((UserDetailIml)principal).getUser().getId();
        if (userRoleService.checkRoleAdminForGroup(userID,userRole.getGroup_id()) != null) {
            try {
                userRoleService.setRoleAdmin(userRole.getUser_id(),userRole.getGroup_id());
                return new ResponseEntity<>("Set role admin is success",HttpStatus.OK);
            }catch (Exception e) {
                return new ResponseEntity<>("Set role admin was unsuccessful",HttpStatus.CONFLICT);
            }

        } else {
            return new ResponseEntity<>("You are not authorized.",HttpStatus.FORBIDDEN);
        }
        // return new ResponseEntity<>("Set role admin was unsuccessful",HttpStatus.CONFLICT);

    }
}
