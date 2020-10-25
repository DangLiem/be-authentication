package com.hdtcn.authentication.service;

import com.hdtcn.authentication.entity.User;
import com.hdtcn.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> saveUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteUser(Integer id) {
        try {
            if (userRepository.existsById(id)) {
//                userRoleService.deleteUserRoleByUserId(id);
                userRepository.deleteById(id);
                return "Delete User id: " + id;
            }
        } catch (Exception e) {
            return "Delete user: " + id + " error " + e.getMessage();
        }
        return "Delete user: " + id + " error ";
    }

    @Override
    public User updateUser(User user) {
        User existUser = userRepository.findById(user.getId()).orElse(null);
        if (existUser != null) {
            existUser.setEmail(user.getEmail());
            existUser.setMssv(user.getMssv());
            existUser.setPassword(user.getPassword());
            existUser.setName(user.getName());
            return userRepository.save(existUser);
        }
        return null;
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
