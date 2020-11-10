package com.hdtcn.authentication.repository;

import com.hdtcn.authentication.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRoleRepository  extends JpaRepository<UserRole, Integer> {
    @Query("SELECT u FROM UserRole u where u.user_id = :userID and u.group_id = :groupID and u.role_id = 2")
    UserRole checkRoleAdminForGroup(@Param("userID") Integer userId, @Param("groupID") Integer groupId);
    @Query("SELECT u FROM UserRole u where u.user_id = :userID and u.group_id = :groupID and u.role_id = :roleID")
    UserRole checkUserExists(@Param("userID") Integer userId, @Param("groupID") Integer groupId, @Param("roleID") Integer roleID);
    @Transactional
    @Modifying
    @Query("UPDATE UserRole u SET u.role_id = 2  where u.user_id = :userID and u.group_id = :groupID")
    void setRoleAdmin(@Param("userID") Integer userId, @Param("groupID") Integer groupId);

}
