package com.soap_hrm.business.service;

import com.soap_hrm.business.dto.EmployeeDto;
import com.soap_hrm.business.dto.requests_dtos.EmployeeRequest;
import com.soap_hrm.business.mappers.EmployeeMapper;
import com.soap_hrm.persistence.connection.JPAManager;
import com.soap_hrm.persistence.entities.*;
import com.soap_hrm.persistence.repo.*;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class EmployeeService {

    EntityManager em;
    EmployeeRepo employeeRepo;

    public EmployeeService() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        employeeRepo = new EmployeeRepo(em);
    }

    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll(Employee.class);

        return employees.stream()
                .map(employee -> EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class))
                .toList();
    }

    public EmployeeDto getEmployeeById(int id) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        if (employee != null)
            return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
        return null;
    }

    public List<EmployeeDto> getEmployeesByName(String name) {
        List<Employee> employees = employeeRepo.findByName(name);

        return employees.stream()
                .map(employee -> EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class))
                .toList();
    }

    public List<EmployeeDto> getEmployeesWithDepartmentId(int id) {
        List<Employee> employees = employeeRepo.findEmployeesWithDepartmentID(id);

        return employees.stream()
                .map(employee -> EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class))
                .toList();
    }

    public String createEmployee(EmployeeRequest employeeRequest) {
        String result = isEmployeeRequestValid(employeeRequest);
        if (!result.isEmpty()) {
            return result;
        } else {

            Job job = getJobById(employeeRequest.getJobID());
            if (job == null) {
                return "there is no job with this id";
            }

            Salary salary = getOrCreateNewSalary(employeeRequest.getSalary());

            Department department = getDepartmentById(employeeRequest.getDepartmentID());
            if (department == null) {
                return "there is no department with this id";
            }

            Address address = getAddressByID(employeeRequest.getAddressID());
            if (address == null) {
                return "there is no address with this id";
            }
            Employee newEmployee = new Employee();
            newEmployee.setEmpName(employeeRequest.getEmpName());
            newEmployee.setEmpEmail(employeeRequest.getEmpEmail());
            newEmployee.setHireDate(employeeRequest.getHireDate());
            newEmployee.setEndDate(employeeRequest.getEndDate());
            newEmployee.setJob(job);
            newEmployee.setSalary(salary);
            newEmployee.setDepartment(department);
            newEmployee.setAddress(address);
            newEmployee.setAnnualHolidays(employeeRequest.getAnnualHolidays());

            employeeRepo.save(newEmployee);
        }

        return "";
    }

    private Address getAddressByID(int addressId) {
        AddressRepo addressRepo = new AddressRepo(em);
        return addressRepo.findById(Address.class, addressId);
    }

    private Department getDepartmentById(int departmentID) {
        DepartmentRepo departmentRepo = new DepartmentRepo(em);
        return departmentRepo.findById(Department.class, departmentID);
    }

    private Salary createSalary(BigDecimal salary) {
        SalaryRepo salaryRepo = new SalaryRepo(em);
        Salary salaryObj = new Salary(salary);
        return salaryRepo.save(salaryObj);
    }

    private Job getJobById(int jobID) {
        JobRepo jobRepo = new JobRepo(em);
        return jobRepo.findById(Job.class, jobID);
    }

    private String isEmployeeRequestValid(EmployeeRequest request) {
        String result = "";
        if (request.getEmpName() == null || request.getEmpName().length() > 60) {
            return "Employee name is invalid";
        }

        if (request.getEmpEmail() == null || request.getEmpEmail().length() > 30) {
            return "email is invalid";
        }

        if (request.getJobID() == 0) {
            return "job Id is invalid";
        }

        if (request.getSalary() == null) {
            return "salary is invalid";
        }

        if (request.getHireDate() == null) {
            return "HireDate  is invalid";
        }

        if (request.getEndDate() == null) {
            return "EndDate  is invalid";
        }

        if (request.getDepartmentID() == 0) {
            return "department Id is invalid";
        }

        if (request.getAddressID() == 0) {
            return "address Id is invalid";
        }
        if (request.getAnnualHolidays() == 0) {
            return "annual holidays number is invalid";
        }
        return result;
    }

    public EmployeeDto updateEmployeeName(int id, String name) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        employee.setEmpName(name);
        employeeRepo.update(employee);
        return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeEmail(int id, String email) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        employee.setEmpEmail(email);
        employeeRepo.update(employee);
        return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeJob(int id, int jobId) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        Job job = new JobRepo(em).findById(Job.class, jobId);
        if (job != null) {
            employee.setJob(job);
            employeeRepo.update(employee);
            return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
        }
        return null;
    }

    public EmployeeDto updateEmployeeSalary(int id, BigDecimal salaryAmount) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        Salary salary = getOrCreateNewSalary(salaryAmount);
        employee.setSalary(salary);
        employeeRepo.update(employee);
        return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeEndDate(int id, LocalDate endDate) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        employee.setEndDate(endDate);
        employeeRepo.update(employee);
        return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployeeDepartment(int id, int departmentId) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        Department department = new DepartmentRepo(em).findById(Department.class, departmentId);
        if (department != null) {
            employee.setDepartment(department);
            employeeRepo.update(employee);
            return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
        }
        return null;
    }

    public EmployeeDto updateEmployeeAddress(int id, int addressId) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        Address address = new AddressRepo(em).findById(Address.class, addressId);
        if (address != null) {
            employee.setAddress(address);
            employeeRepo.update(employee);
            return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
        }
        return null;
    }

    public EmployeeDto updateEmployeeAnnualHolidays(int id, int annualHolidays) {
        Employee employee = employeeRepo.findById(Employee.class, id);
        employee.setAnnualHolidays(annualHolidays);
        employeeRepo.update(employee);
        return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
    }

    private Salary getOrCreateNewSalary(BigDecimal salaryAmount) {
        SalaryRepo salaryRepo = new SalaryRepo(em);
        Salary salary = salaryRepo.findSalaryByAmount(salaryAmount);
        if (salary == null) {
            salary = salaryRepo.save(new Salary(salaryAmount));
        }
        return salary;
    }

    public void deleteById(int id){
        employeeRepo.deleteById(Employee.class,id);
    }
    public void deleteAllEmployees(){
        employeeRepo.deleteAll();
    }
}
