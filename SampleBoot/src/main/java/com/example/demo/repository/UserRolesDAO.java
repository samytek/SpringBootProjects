package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserRoles;

public interface UserRolesDAO extends JpaRepository<UserRoles, Long> {

	public UserRoles findByUserIdAndRoleId(int userId, int roleId);

}