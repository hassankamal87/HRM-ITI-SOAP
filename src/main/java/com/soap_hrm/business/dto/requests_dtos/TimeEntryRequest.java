package com.soap_hrm.business.dto.requests_dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.TimeEntry}
 */
public class TimeEntryRequest implements Serializable {
    private Integer id;
    @NotNull
    private int empId;
    @NotNull
    private LocalDate entryDate;
    private LocalTime timeIn;
    private LocalTime timeOut;

    public TimeEntryRequest() {
    }

    public TimeEntryRequest(Integer id, int empID, LocalDate entryDate, LocalTime timeIn, LocalTime timeOut) {
        this.id = id;
        this.empId = empID;
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

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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
        TimeEntryRequest entity = (TimeEntryRequest) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.empId, entity.empId) &&
                Objects.equals(this.entryDate, entity.entryDate) &&
                Objects.equals(this.timeIn, entity.timeIn) &&
                Objects.equals(this.timeOut, entity.timeOut);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empId, entryDate, timeIn, timeOut);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "empID = " + empId + ", " +
                "entryDate = " + entryDate + ", " +
                "timeIn = " + timeIn + ", " +
                "timeOut = " + timeOut + ")";
    }
}