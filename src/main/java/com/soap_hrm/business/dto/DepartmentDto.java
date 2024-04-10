package com.soap_hrm.business.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.Department}
 */
public class DepartmentDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 45)
    private String depName;
    @NotNull
    private MgrDto depMGR;

    public DepartmentDto() {
    }

    public DepartmentDto(Integer id, String depName, MgrDto depMGR) {
        this.id = id;
        this.depName = depName;
        this.depMGR = depMGR;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public MgrDto getDepMGR() {
        return depMGR;
    }

    public void setDepMGR(MgrDto depMGR) {
        this.depMGR = depMGR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentDto entity = (DepartmentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.depName, entity.depName) &&
                Objects.equals(this.depMGR, entity.depMGR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, depName, depMGR);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "depName = " + depName + ", " +
                "depMGR = " + depMGR + ")";
    }
}