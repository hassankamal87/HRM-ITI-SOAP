package com.soap_hrm.business.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.TimeEntry}
 */
public class TimeEntryDto implements Serializable {
    private Integer id;
    @NotNull
    private EmployeeDto employee;
    @NotNull
    private LocalDate entryDate;
    private LocalTime timeIn;
    private LocalTime timeOut;

    public TimeEntryDto() {
    }

    public TimeEntryDto(Integer id, EmployeeDto empID, LocalDate entryDate, LocalTime timeIn, LocalTime timeOut) {
        this.id = id;
        this.employee = empID;
        this.entryDate = entryDate;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
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

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntryDto entity = (TimeEntryDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.employee, entity.employee) &&
                Objects.equals(this.entryDate, entity.entryDate) &&
                Objects.equals(this.timeIn, entity.timeIn) &&
                Objects.equals(this.timeOut, entity.timeOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, entryDate, timeIn, timeOut);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "empID = " + employee + ", " +
                "entryDate = " + entryDate + ", " +
                "timeIn = " + timeIn + ", " +
                "timeOut = " + timeOut + ")";
    }
}