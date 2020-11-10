package com.hdtcn.authentication.service;

import com.hdtcn.authentication.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRoleService {
    UserRole checkRoleAdminForGroup(Integer userID, Integer groupId);
    List<UserRole> getAllUserRole();
    UserRole addUserRole(UserRole userRole);
    boolean checkUserExists(Integer userID, Integer groupID, Integer roleID);
    void setRoleAdmin(Integer userID, Integer groupID);
}
