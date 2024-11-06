package com.example.SpringBootRedis.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users implements Serializable {
    private String userId;
    private String name;
    private String phone;
    private String email;
}
