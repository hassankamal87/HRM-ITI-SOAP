package com.soap_hrm.business.dto.requests_dtos;


public class DepartmentRequest {
    private String depName;
    private int depMGRId;

    public DepartmentRequest() {
    }

    public DepartmentRequest(String depName, int depMGRId) {
        this.depName = depName;
        this.depMGRId = depMGRId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public int getDepMGRId() {
        return depMGRId;
    }

    public void setDepMGRId(int depMGRId) {
        this.depMGRId = depMGRId;
    }
}
