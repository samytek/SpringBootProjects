package com.example.SpringBootBatchProcessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employees {
    private String firstName;
    private String gender;
    private String startDate;
    private String lastLoginTime;
    private String salary;
    private String bonus;
    private String seniorManagement;
    private String team;
}
