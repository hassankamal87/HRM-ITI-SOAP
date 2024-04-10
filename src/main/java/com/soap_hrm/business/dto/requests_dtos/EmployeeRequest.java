package com.soap_hrm.business.dto.requests_dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeRequest {
    @Size(max = 60)
    @NotNull
    private String empName;

    @Size(max = 30)
    @NotNull
    private String empEmail;

    @NotNull
    private int jobID;

    @NotNull
    private BigDecimal salary;

    @NotNull
    private LocalDate hireDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private int departmentID;
    @NotNull
    private int addressID;

    private int annualHolidays;


    public EmployeeRequest() {
    }

    public EmployeeRequest(String empName, String empEmail, int jobID, BigDecimal salary, LocalDate hireDate, LocalDate endDate, int departmentID, int addressID) {
        this.empName = empName;
        this.empEmail = empEmail;
        this.jobID = jobID;
        this.salary = salary;
        this.hireDate = hireDate;
        this.endDate = endDate;
        this.departmentID = departmentID;
        this.addressID = addressID;
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

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
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

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getAnnualHolidays() {
        return annualHolidays;
    }

    public void setAnnualHolidays(int annualHolidays) {
        this.annualHolidays = annualHolidays;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", jobID=" + jobID +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                ", endDate=" + endDate +
                ", departmentID=" + departmentID +
                ", addressID=" + addressID +
                ", annualHolidays=" + annualHolidays +
                '}';
    }
}
