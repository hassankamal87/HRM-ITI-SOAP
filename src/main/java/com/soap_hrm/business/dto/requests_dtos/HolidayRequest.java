package com.soap_hrm.business.dto.requests_dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class HolidayRequest implements Serializable {
    private Integer id;
    @NotNull
    private int employeeId;
    @NotNull
    private LocalDate holidayDate;
    @Size(max = 255)
    private String description;

    public HolidayRequest() {
    }

    public HolidayRequest(Integer id, int employee, LocalDate holidayDate, String description) {
        this.id = id;
        this.employeeId = employee;
        this.holidayDate = holidayDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayRequest entity = (HolidayRequest) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.employeeId, entity.employeeId) &&
                Objects.equals(this.holidayDate, entity.holidayDate) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, holidayDate, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "employee = " + employeeId + ", " +
                "holidayDate = " + holidayDate + ", " +
                "description = " + description + ")";
    }
}