package com.example.SpringSecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Student {

    private int id;
    private String name;
    private int age;
}
