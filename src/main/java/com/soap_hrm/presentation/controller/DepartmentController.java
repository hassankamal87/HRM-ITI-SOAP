package com.soap_hrm.presentation.controller;

import com.soap_hrm.business.dto.DepartmentDto;
import com.soap_hrm.business.dto.EmployeeDto;
import com.soap_hrm.business.dto.requests_dtos.DepartmentRequest;
import com.soap_hrm.business.service.DepartmentService;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.util.List;

@WebService
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController() {
        departmentService = new DepartmentService();
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    public DepartmentDto getDepartmentById(@WebParam(name = "employeeId") int id) {
        DepartmentDto department = departmentService.getDepartmentById(id);
        if (department != null) {
            return department;
        } else {
            throwSoapFault("Invalid department Id");
            return null;
        }
    }

    public List<DepartmentDto> getDepartmentsByName(@WebParam(name = "employeeName") String name) {
        return departmentService.getDepartmentsStartsWithName(name);
    }

    public EmployeeDto getDepartmentManager(@WebParam(name = "employeeId") int id) {
        if (id == 0) {
            throwSoapFault("Invalid department Id");
            return null;
        }
        EmployeeDto employeeDto = departmentService.getDepartmentManager(id);
        if (employeeDto != null)
            return employeeDto;

        throwSoapFault("Invalid department Id");
        return null;
    }

    public List<DepartmentDto> getDepartmentByManagerName(@WebParam(name = "managerName") String name) {
        return departmentService.getDepartmentsByManagerName(name);
    }

    public DepartmentDto getDepartmentByManagerId(@WebParam(name = "ManagerId") int id) {
        DepartmentDto department = departmentService.getDepartmentByManagerID(id);
        if (department != null) {
            return department;
        } else {
            throwSoapFault("Invalid manager Id");
            return null;
        }
    }

    public DepartmentDto createDepartment(@WebParam(name = "departmentData")DepartmentRequest departmentRequest) {
        if (departmentRequest == null) {
            throwSoapFault("no body");
            return null;
        }
        DepartmentDto result = departmentService.createDepartment(departmentRequest);
        if (result!=null)
            return result;
        else{
            throwSoapFault("invalid data");
            return null;
        }
    }

    public DepartmentDto updateDepartmentName(@WebParam(name = "depId") int id, @WebParam(name = "depname") String depName) {
        DepartmentDto department = departmentService.updateDepartmentName(id, depName);
        if (department != null)
            return department;
        else{
            throwSoapFault("invalid department Id");
            return null;
        }
    }

    public DepartmentDto updateDepartmentManager(@WebParam(name = "depId") int depId, @WebParam(name = "mgrId") int mgrId) {
        DepartmentDto departmentDto = departmentService.updateDepartmentManager(depId, mgrId);
        if (departmentDto != null)
            return departmentDto;
        else{
            throwSoapFault("invalid department Id");
            return null;
        }
    }

    public void deleteAllEmployees() {
        boolean result = departmentService.deleteAllDepartments();
        if (!result)
            throwSoapFault("cannot delete all department");
    }

    public void deleteDepartmentById(@WebParam(name = "departmentId") int id) {
        boolean result = departmentService.deleteDepartmentById(id);
        if (!result)
            throwSoapFault("this department has employees, can't delete it");
    }

    private void throwSoapFault(String message) {
        try {
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString(message);
            throw new SOAPFaultException(fault);
        } catch (Exception e) {
            throw new RuntimeException(message);
        }
    }
}
