package com.example.SpringSecurity.controller;

import com.example.SpringSecurity.entity.Student;
import com.example.SpringSecurity.entity.Users;
import com.example.SpringSecurity.repository.UserRepository;
import com.example.SpringSecurity.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class SecurityController {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;


    List<Student> studentList = new ArrayList<>(Arrays.asList(
            new Student(01, "Sameer", 25),
            new Student(02, "Shaikh", 26))
    );

    @GetMapping("/")
    public String getMessage() {
        return "Hello Welcome to Security Controller";
    }

    @GetMapping("/getCsrfToken")
    public CsrfToken getCsrfToken(HttpServletRequest servletRequest) {
        return (CsrfToken) servletRequest.getAttribute("_csrf");
    }

    @PostMapping("/addStudent")
    public List<Student> addStudent(@RequestBody Student student) {
        studentList.add(student);
        return studentList;
    }

    @PostMapping("/registerUser")
    public Users registerUser(@RequestBody Users users) throws Exception {
        Users users1 = userDetailsServiceImpl.registerUser(users);
        return users1;
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        return userDetailsServiceImpl.validateUser(users);
    }

    @PostMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return userDetailsServiceImpl.getAllUsers();
    }
}
