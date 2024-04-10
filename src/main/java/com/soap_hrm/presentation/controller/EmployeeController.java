package com.soap_hrm.presentation.controller;

import com.soap_hrm.business.dto.EmployeeDto;
import com.soap_hrm.business.dto.requests_dtos.EmployeeRequest;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebService
public class EmployeeController {

    com.soap_hrm.business.service.EmployeeService employeeService;

    public EmployeeController() {
        employeeService = new com.soap_hrm.business.service.EmployeeService();
    }


    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public EmployeeDto getEmployeeById(@WebParam(name = "employeeId") int id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return employee;
        } else {
            throwSoapFault("Employee Not Found");
            return null;
        }
    }

    public List<EmployeeDto> getEmployeesByDepartmentId(@WebParam(name = "departmentId") int id) {
        return employeeService.getEmployeesWithDepartmentId(id);
    }

    public List<EmployeeDto> getEmployeesStartsWithName(@WebParam(name = "employeeName") String name) {
        return employeeService.getEmployeesByName(name);
    }

    public EmployeeDto createEmployee(@WebParam(name = "employeeData")EmployeeRequest request) {

        if (request == null) {
            throwSoapFault("no body");
            return null;
        }
        EmployeeDto dto = employeeService.createEmployee(request);
        if (dto != null) {
            return dto;
        } else {
            throwSoapFault("invalid data");
            return null;
        }
    }

    public EmployeeDto updateEmployeeName(@WebParam(name ="employeeId") int id,@WebParam(name ="employeeName") String empName) {
        return employeeService.updateEmployeeName(id, empName);
    }

    public EmployeeDto updateEmployeeEmail(@WebParam(name ="employeeId") int id, @WebParam(name ="newEmail") String empEmail) {
        return employeeService.updateEmployeeEmail(id, empEmail);
    }


    public EmployeeDto updateEmployeeJob(@WebParam(name ="employeeID") int id, @WebParam(name ="jobId") int jobId) {
        EmployeeDto employeeDto = employeeService.updateEmployeeJob(id, jobId);
        if (employeeDto != null) {
            return employeeDto;
        } else {
            throwSoapFault("Invalid job Id");
            return null;
        }
    }

    public EmployeeDto updateEmployeeSalary(@WebParam(name ="employeeID") int id, @WebParam(name ="salary") BigDecimal salaryAmount) {
        return employeeService.updateEmployeeSalary(id, salaryAmount);
    }

    public EmployeeDto updateEmployeeEndDate(@WebParam(name ="employeeID") int id, @WebParam(name ="stringDate") String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, formatter);
            return employeeService.updateEmployeeEndDate(id, date);
        } catch (DateTimeParseException e) {
            throwSoapFault("Invalid date format. Please use yyyy-MM-dd");
            return null;
        }
    }


    public EmployeeDto updateEmployeeDepartment(@WebParam(name ="employeeID") int id, @WebParam(name ="departmentId") int departmentId) {
        if(id == 0 || departmentId == 0){
            throwSoapFault("Invalid parameter's, ex. empId=number&departmentId=number");
            return null;
        }
        EmployeeDto employeeDto = employeeService.updateEmployeeDepartment(id, departmentId);
        if (employeeDto != null) {
            return employeeDto;
        } else {
            throwSoapFault("Invalid department Id");
            return null;
        }
    }

    public EmployeeDto updateEmployeeAddress(@WebParam(name ="employeeID") int id, @WebParam(name ="addressID") int addressId) {
        EmployeeDto employeeDto = employeeService.updateEmployeeAddress(id, addressId);
        if (employeeDto != null) {
            return employeeDto;
        } else {
            throwSoapFault("Invalid Address Id");
            return null;
        }
    }

    public EmployeeDto updateEmployeeAnnualHolidaysNumber(@WebParam(name ="employeeID") int id, @WebParam(name ="annualNumber") int annualNumber) {
        return employeeService.updateEmployeeAnnualHolidays(id, annualNumber);
    }

    public void deleteAllEmployees() {
        employeeService.deleteAllEmployees();
    }

    public void deleteEmployeeById(@WebParam(name ="employeeID")int id) {
        employeeService.deleteById(id);
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
