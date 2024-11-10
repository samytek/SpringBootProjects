package com.example.MapStructDemoApp.repository;

import com.example.MapStructDemoApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);
    Employee findByDeptName(String deptName);
    Employee findByDeptNameAndName(String deptName, String name);
}
