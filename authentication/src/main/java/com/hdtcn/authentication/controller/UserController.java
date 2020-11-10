package com.hdtcn.authentication.controller;

import com.hdtcn.authentication.dto.UserDTO;
import com.hdtcn.authentication.entity.User;
import com.hdtcn.authentication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * Hàm dùng để tất cả user dành cho admin của hệ thống
     * @param requestWrapper
     * @return ResponseEntity trả về list<User> Nếu đủ quyền và có kq trả về, không thì trả về message thông báo
     */
    @GetMapping("/users")
    public ResponseEntity<?> getListUser(SecurityContextHolderAwareRequestWrapper requestWrapper) throws ParseException {
        List<User> users = null;
        ArrayList<UserDTO> userDTO = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        if (requestWrapper.isUserInRole("ROLE_ADMIN")) {
            users = userService.getUsers();
            System.out.println(users.size());
            for (int i = 0 ;i < users.size() ; i++) {
                UserDTO uDTO = modelMapper.map(users.get(i),UserDTO.class);
                if (uDTO != null) {
                    userDTO.add(uDTO);
                }
            }

             return new ResponseEntity<>(userDTO,HttpStatus.OK);
        }
        return new ResponseEntity<>("You are not authorized.",HttpStatus.FORBIDDEN);

    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Hàm dùng đẻ get user qua email
     * @param email
     * @return
     */
    @GetMapping("users/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    /**
     * Hàm dùng để trả về phiên session hiện tại của user vừa authenticate
     * @return ResponseEntity
     */
    @GetMapping("/users/current")
    public ResponseEntity<?> userCurrent() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(principal);
    }

    /**
     * hàm dùng để đăng ký user vào hệ thống
     * @param user
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        try {
            if (userService.getUserByEmail(user.getEmail()) != null || userService.findByMssv(user.getMssv()) != null) {
                return new ResponseEntity<String>("Registration failed.",HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Registration failed.",HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return new ResponseEntity<String>("SignUp Success.",HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable int id, @RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/users/mssv/{mssv}")
    public ResponseEntity<?> getUserByMssv(@PathVariable String mssv) {
        return ResponseEntity.ok(userService.findByMssv(mssv));
    }
//    @GetMapping("/{email}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable(name = "mssv") String mssv) {
//        return new ResponseEntity<>(userService.findByUsername(mssv), HttpStatus.OK);
//    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
    
}
