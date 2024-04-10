package com.soap_hrm.business.mappers;

import com.soap_hrm.business.dto.EmployeeDto;
import com.soap_hrm.persistence.entities.Employee;

public class EmployeeMapper extends GenericMapping<Employee, EmployeeDto> {
    private static EmployeeMapper instance;

    private EmployeeMapper(){
        if (instance!=null){
            throw new RuntimeException("wld 3eeb");
        }
    }

    public static synchronized EmployeeMapper getInstance(){
        if (instance == null){
            instance = new EmployeeMapper();
        }
        return instance;
    }
}
