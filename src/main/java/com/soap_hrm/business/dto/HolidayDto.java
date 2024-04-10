package com.soap_hrm.business.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.Holiday}
 */
public class HolidayDto implements Serializable {
    private Integer id;
    @NotNull
    private EmployeeDto employee;
    @NotNull
    private LocalDate holidayDate;
    @Size(max = 255)
    private String description;

    public HolidayDto() {
    }

    public HolidayDto(Integer id, EmployeeDto employee, LocalDate holidayDate, String description) {
        this.id = id;
        this.employee = employee;
        this.holidayDate = holidayDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayDto entity = (HolidayDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.employee, entity.employee) &&
                Objects.equals(this.holidayDate, entity.holidayDate) &&
                Objects.equals(this.description, entity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, holidayDate, description);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "employee = " + employee + ", " +
                "holidayDate = " + holidayDate + ", " +
                "description = " + description + ")";
    }
}