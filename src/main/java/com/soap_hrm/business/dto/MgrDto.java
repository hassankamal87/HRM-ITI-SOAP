package com.soap_hrm.business.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.Employee}
 */
public class MgrDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 60)
    private String empName;

    public MgrDto() {
    }

    public MgrDto(Integer id, String empName) {
        this.id = id;
        this.empName = empName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MgrDto entity = (MgrDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.empName, entity.empName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "empName = " + empName + ")";
    }
}