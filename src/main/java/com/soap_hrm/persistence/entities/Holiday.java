package com.soap_hrm.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "holiday", schema = "hr_db")
public class Holiday {
    @Id
    @Column(name = "holidayID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employeeID", nullable = false)
    private Employee employee;

    @NotNull
    @Column(name = "holidayDate", nullable = false)
    private LocalDate holidayDate;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "id=" + id +
                ", employeeID=" + employee.getEmpName() +
                ", holidyaDate=" + holidayDate +
                ", description='" + description + '\'' +
                '}';
    }
}