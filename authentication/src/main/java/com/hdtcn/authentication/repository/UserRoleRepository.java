package com.hdtcn.authentication.repository;

import com.hdtcn.authentication.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository  extends JpaRepository<UserRole, Integer> {
}
