package com.soap_hrm.business.mappers;

import com.soap_hrm.business.dto.DepartmentDto;
import com.soap_hrm.persistence.entities.Department;

public class DepartmentMapper extends GenericMapping<Department, DepartmentDto> {

    private static DepartmentMapper instance;

    private DepartmentMapper(){
        if (instance!=null){
            throw new RuntimeException("wld 3eeb");
        }
    }

    public static synchronized DepartmentMapper getInstance(){
        if (instance == null){
            instance = new DepartmentMapper();
        }
        return instance;
    }}
