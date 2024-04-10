package com.soap_hrm.business.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.soap_hrm.persistence.entities.Job}
 */
public class JobDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 30)
    private String jobTitle;

    public JobDto() {
    }

    public JobDto(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public JobDto(Integer id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobDto entity = (JobDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.jobTitle, entity.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobTitle);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "jobTitle = " + jobTitle + ")";
    }
}