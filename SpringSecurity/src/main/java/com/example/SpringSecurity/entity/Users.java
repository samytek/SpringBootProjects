package com.example.SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_seq_new", allocationSize = 1)
    private Long id;

    @Column(name = "username", unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;
}
