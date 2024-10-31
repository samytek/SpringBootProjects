package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.UserSession;

public interface UserSessionDAO extends JpaRepository<UserSession, Long> {
	
	UserSession findByUserIdAndCurrentRoleAndAccessToken(int usId, String currRole, String accessToken);
	UserSession findByUserIdAndCurrentRole(int usId, String currRole);

}