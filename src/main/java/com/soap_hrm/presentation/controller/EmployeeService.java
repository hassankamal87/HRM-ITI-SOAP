package com.soap_hrm.presentation.controller;

import com.soap_hrm.business.dto.EmployeeDto;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public class EmployeeService {

    com.soap_hrm.business.service.EmployeeService employeeService;

    public EmployeeService() {
        employeeService = new com.soap_hrm.business.service.EmployeeService();
    }


    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public EmployeeDto getEmployeeById(int id) {
        EmployeeDto employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return employee;
        } else {
            return null;
        }
    }
//
//    public Response getEmployeesByDepartmentId(@PathParam("id") int id) {
//        return Response.ok(employeeService.getEmployeesWithDepartmentId(id)).build();
//    }
//
//    public Response getEmployeesStartsWithName(@QueryParam("name") String name) {
//        return Response.ok(employeeService.getEmployeesByName(name)).build();
//    }
//
//    public Response createEmployee(@Valid EmployeeRequest request) {
//
//        if (request == null){
//            return Response.status(Response.Status.BAD_REQUEST).entity("there is no body").build();
//        }
//        String result = employeeService.createEmployee(request);
//        if (result.isEmpty()) {
//            return Response.ok().build();
//        } else {
//            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
//        }
//    }
//
//    public Response updateEmployeeName(@QueryParam("empId") int id, @QueryParam("name") String empName) {
//        return Response.ok(employeeService.updateEmployeeName(id, empName)).build();
//    }
//
//    public Response updateEmployeeEmail(@QueryParam("empId") int id, @QueryParam("email") String empEmail) {
//        return Response.ok(employeeService.updateEmployeeEmail(id, empEmail)).build();
//    }
//
//
//    public Response updateEmployeeJob(@QueryParam("empId") int id, @QueryParam("jobId") int jobId) {
//        EmployeeDto employeeDto = employeeService.updateEmployeeJob(id, jobId);
//        if (employeeDto != null) {
//            return Response.ok(employeeDto).build();
//        } else {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid job Id").build();
//        }
//    }
//
//    public Response updateEmployeeSalary(@QueryParam("empId") int id, @QueryParam("salary") BigDecimal salaryAmount) {
//        return Response.ok(employeeService.updateEmployeeSalary(id, salaryAmount)).build();
//    }
//
//    public Response updateEmployeeEndDate(@QueryParam("empId") int id, @QueryParam("date") String dateStr) {
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate date = LocalDate.parse(dateStr, formatter);
//            return Response.ok(employeeService.updateEmployeeEndDate(id, date)).build();
//        } catch (DateTimeParseException e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid date format. Please use yyyy-MM-dd").build();
//        }
//    }
//
//    public Response updateEmployeeDepartment(@QueryParam("empId") int id, @QueryParam("departmentId") int departmentId) {
//        if(id == 0 || departmentId == 0){
//            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid parameter's, ex. empId=number&departmentId=number").build();
//        }
//        EmployeeDto employeeDto = employeeService.updateEmployeeDepartment(id, departmentId);
//        if (employeeDto != null) {
//            return Response.ok(employeeDto).build();
//        } else {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid department Id").build();
//        }
//    }
//
//    public Response updateEmployeeAddress(@QueryParam("empId") int id, @QueryParam("addressId") int addressId) {
//        EmployeeDto employeeDto = employeeService.updateEmployeeAddress(id, addressId);
//        if (employeeDto != null) {
//            return Response.ok(employeeDto).build();
//        } else {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Address Id").build();
//        }
//    }
//
//    public Response updateEmployeeSalary(@QueryParam("empId") int id, @QueryParam("annual") int annualNumber) {
//        return Response.ok(employeeService.updateEmployeeAnnualHolidays(id, annualNumber)).build();
//    }
//
//    public Response deleteAllEmployees() {
//        employeeService.deleteAllEmployees();
//        return Response.ok().build();
//    }
//
//
//    public void deleteEmployeeById(int id) {
//        employeeService.deleteById(id);
//    }
}
