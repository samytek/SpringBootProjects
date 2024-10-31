package com.example.demo.repository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
}

