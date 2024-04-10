package com.soap_hrm.business.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.Employee}
 */
public class EmployeeDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 60)
    private String empName;
    @NotNull
    @Size(max = 30)
    private String empEmail;
    @NotNull
    private JobDto job;
    @NotNull
    private SalaryDto salary;
    @NotNull
    private LocalDate hireDate;
    private LocalDate endDate;

    private DepartmentDto department;
    @NotNull
    private AddressDto address;
    private Integer annualHolidays;

    public EmployeeDto() {
    }

    public EmployeeDto(Integer id, String empName, String empEmail, JobDto jobID, SalaryDto salary, LocalDate hireDate, LocalDate endDate, DepartmentDto department, AddressDto addressID, Integer annualHolidays) {
        this.id = id;
        this.empName = empName;
        this.empEmail = empEmail;
        this.job = jobID;
        this.salary = salary;
        this.hireDate = hireDate;
        this.endDate = endDate;
        this.department = department;
        this.address = addressID;
        this.annualHolidays = annualHolidays;
    }

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

    public JobDto getJob() {
        return job;
    }

    public void setJob(JobDto job) {
        this.job = job;
    }

    public SalaryDto getSalary() {
        return salary;
    }

    public void setSalary(SalaryDto salary) {
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

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Integer getAnnualHolidays() {
        return annualHolidays;
    }

    public void setAnnualHolidays(Integer annualHolidays) {
        this.annualHolidays = annualHolidays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDto entity = (EmployeeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.empName, entity.empName) &&
                Objects.equals(this.empEmail, entity.empEmail) &&
                Objects.equals(this.job, entity.job) &&
                Objects.equals(this.salary, entity.salary) &&
                Objects.equals(this.hireDate, entity.hireDate) &&
                Objects.equals(this.endDate, entity.endDate) &&
                Objects.equals(this.department.getId(), entity.department.getId()) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.annualHolidays, entity.annualHolidays);
    }

    @Override
    public String toString() {
        String depMGR = "not exist";
        if (department != null){
            depMGR = department.getDepName();
        }
        return "EmployeeDto{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", empEmail='" + empEmail + '\'' +
                ", jobID=" + job +
                ", salaryID=" + salary +
                ", hireDate=" + hireDate +
                ", endDate=" + endDate +
                ", department=" + depMGR +
                ", address=" + address +
                ", annualHolidays=" + annualHolidays +
                '}';
    }
}