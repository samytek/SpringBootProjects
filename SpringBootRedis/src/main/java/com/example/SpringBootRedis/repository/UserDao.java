package com.example.SpringBootRedis.repository;

import com.example.SpringBootRedis.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    private static final String KEY = "USER312412";
//
//    public Users save(Users user) {
//        redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
//        return user;
//    }
//
//    public Users get(String userId) {
//        return (Users) redisTemplate.opsForHash().get(KEY, userId);
//    }
//
//    public Map<Object, Object> findAll() {
//        return redisTemplate.opsForHash().entries(KEY);
//    }
//
//    public void delete(String userId) {
//        redisTemplate.opsForHash().delete(KEY, userId);
//    }
}