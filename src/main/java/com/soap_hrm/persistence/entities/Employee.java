package com.soap_hrm.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "employee", schema = "hr_db")
public class Employee {
    @Id
    @Column(name = "empID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 60)
    @NotNull
    @Column(name = "empName", nullable = false, length = 60)
    private String empName;

    @Size(max = 30)
    @NotNull
    @Column(name = "empEmail", nullable = false, length = 30)
    private String empEmail;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jobID", nullable = false)
    private Job job;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "salaryID", nullable = false)
    private Salary salary;

    @NotNull
    @Column(name = "hireDate", nullable = false)
    private LocalDate hireDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depID")
    private Department department;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "addressID", nullable = false)
    private Address address;

    @Column(name = "annualHolidays")
    private Integer annualHolidays;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getAnnualHolidays() {
        return annualHolidays;
    }

    public void setAnnualHolidays(Integer annualHolidays) {
        this.annualHolidays = annualHolidays;
    }

    public void decrementAnnualHoliday(){
        annualHolidays--;
    }
    @Override
    public String toString() {
        String depMGR = "not exist";
        if (department != null){
            depMGR = department.getDepName();
        }
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", jobID=" + job +
                ", salaryID=" + salary +
                ", hireDate=" + hireDate +
                ", endDate=" + endDate +
                ", department=" + depMGR +
                ", addressID=" + address +
                ", annualHolidays=" + annualHolidays +
                '}';
    }
}