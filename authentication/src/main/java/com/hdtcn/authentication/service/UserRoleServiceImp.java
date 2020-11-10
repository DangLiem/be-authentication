package com.hdtcn.authentication.service;

import com.hdtcn.authentication.entity.UserRole;
import com.hdtcn.authentication.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRoleServiceImp implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Override
    public UserRole checkRoleAdminForGroup(Integer userID, Integer groupID) {
        return userRoleRepository.checkRoleAdminForGroup(userID,groupID);
    }

    @Override
    public List<UserRole> getAllUserRole() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public boolean checkUserExists(Integer userID, Integer groupID, Integer roleID) {
        return userRoleRepository.checkUserExists(userID, groupID, roleID) != null;
    }

    @Override
    public void setRoleAdmin(Integer userID, Integer groupID) {
        userRoleRepository.setRoleAdmin(userID,groupID);
    }

}
