package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Employees_Details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orc_seq_gen")
    @SequenceGenerator(name = "orc_seq_gen", sequenceName = "SEQ_Employees_Details", allocationSize = 1)
    private int id;

    @JsonProperty("First Name")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("Gender")
    @Column(name = "gender")
    private String gender;

    @JsonProperty("Start Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    @Column(name = "start_date")
    private Date startDate;

    @JsonProperty("Last Login Time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "h:mm a")
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @JsonProperty("Salary")
    @Column(name = "salary")
    private Double salary;

    @JsonProperty("Bonus")
    @Column(name = "bonus")
    private Double bonus;

    @JsonProperty("Senior Management")
    @Column(name = "senior_management")
    private Boolean seniorManagement;

    @JsonProperty("Team")
    @Column(name = "team")
    private String team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Boolean getSeniorManagement() {
        return seniorManagement;
    }

    public void setSeniorManagement(Boolean seniorManagement) {
        this.seniorManagement = seniorManagement;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "EmployeesDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", startDate=" + startDate +
                ", lastLoginTime=" + lastLoginTime +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", seniorManagement=" + seniorManagement +
                ", team='" + team + '\'' +
                '}';
    }
}
