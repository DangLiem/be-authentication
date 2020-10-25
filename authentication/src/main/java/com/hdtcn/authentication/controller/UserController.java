package com.hdtcn.authentication.controller;

import com.hdtcn.authentication.entity.User;
import com.hdtcn.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> getListUser() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
//    @GetMapping("/current")
//    public ResponseEntity<?> userCurrent() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        return ResponseEntity.ok(principal);
//    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveUser(user);
       return ResponseEntity.ok(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable int id, @RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }
//    @GetMapping("/{email}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable(name = "mssv") String mssv) {
//        return new ResponseEntity<>(userService.findByUsername(mssv), HttpStatus.OK);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    
}
