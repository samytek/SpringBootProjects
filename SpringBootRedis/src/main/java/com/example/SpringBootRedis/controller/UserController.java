package com.example.SpringBootRedis.controller;

import com.example.SpringBootRedis.repository.UserDao;
import com.example.SpringBootRedis.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

//    @Autowired
//    private UserDao userDao;
//
//    @PostMapping
//    public Users createUser(@RequestBody Users user) {
//        user.setUserId(UUID.randomUUID().toString());
//        return userDao.save(user);
//    }
//
//    @GetMapping("/{userId}")
//    public Users getUser(@PathVariable("userId") String userId) {
//        return userDao.get(userId);
//    }
//
//    @GetMapping
//    public List<Users> getAll() {
//        Map<Object, Object> all = userDao.findAll();
//        Collection<Object> values = all.values();
//        return values.stream().map(value -> (Users) value).collect(Collectors.toList());
//    }
//
//    @DeleteMapping("/{userId}")
//    public void deleteUser(@PathVariable String userId) {
//        userDao.delete(userId);
//    }
}