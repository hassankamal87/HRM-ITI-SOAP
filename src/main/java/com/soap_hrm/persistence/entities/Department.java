package com.soap_hrm.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "department", schema = "hr_db")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "depID", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "depName", nullable = false, length = 45)
    private String depName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depMGR", nullable = false)
    private Employee depMGR;

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

    public Employee getDepMGR() {
        return depMGR;
    }

    public void setDepMGR(Employee depMGR) {
        this.depMGR = depMGR;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depName='" + depName + '\'' +
                ", depMGR=" + depMGR.getEmpName() +
                '}';
    }
}