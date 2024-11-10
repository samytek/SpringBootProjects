package com.example.MapStructDemoApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "EMPLOYEE_MAP_STRUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "SEQ_EMPLOYEE_MAP", allocationSize = 1)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "deptName")
    private String deptName;

    @Column(name = "age")
    private int age;

    @Column(name = "email_Address")
    private String emailAddress;

    @Column(name = "project_Id")
    private int projectId;

    @Column(name = "creation_Date")
    private Date creationDate;
}