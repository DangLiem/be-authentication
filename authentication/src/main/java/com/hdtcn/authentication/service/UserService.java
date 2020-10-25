package com.hdtcn.authentication.service;

import com.hdtcn.authentication.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User saveUser(User user);
    public List<User> saveUsers(List<User> users);
    public List<User> getUsers();
    public User getUserById(Integer id);
    public String deleteUser(Integer id);
    public User updateUser(User user);
    public User getUserByEmail(String email);
}
