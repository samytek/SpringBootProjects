package com.example.SpringBootBatchProcessingJPA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "employees_data")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "employees_seq", allocationSize = 1)
    private int id;
    private String firstName;
    private String gender;
    private String startDate;
    private String lastLoginTime;
    private String salary;
    private String bonus;
    private String seniorManagement;
    private String team;
}
