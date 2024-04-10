package com.soap_hrm.business.service;

import com.soap_hrm.business.dto.DepartmentDto;
import com.soap_hrm.business.dto.EmployeeDto;
import com.soap_hrm.business.dto.requests_dtos.DepartmentRequest;
import com.soap_hrm.business.mappers.DepartmentMapper;
import com.soap_hrm.business.mappers.EmployeeMapper;
import com.soap_hrm.persistence.connection.JPAManager;
import com.soap_hrm.persistence.entities.*;
import com.soap_hrm.persistence.repo.DepartmentRepo;
import com.soap_hrm.persistence.repo.EmployeeRepo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DepartmentService {
    EntityManager em;
    DepartmentRepo departmentRepo;

    public DepartmentService() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        departmentRepo = new DepartmentRepo(em);
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll(Department.class);
        return departments.stream()
                .map(department -> DepartmentMapper.getInstance().mapEntityToDto(department, DepartmentDto.class))
                .toList();
    }

    public DepartmentDto getDepartmentById(int id) {
        Department department = departmentRepo.findById(Department.class, id);
        if (department == null)
            return null;
        return DepartmentMapper.getInstance().mapEntityToDto(department, DepartmentDto.class);
    }

    public List<DepartmentDto> getDepartmentsStartsWithName(String name) {
        List<Department> departments = departmentRepo.findDepartmentsByName(name);
        return departments.stream()
                .map(department -> DepartmentMapper.getInstance().mapEntityToDto(department, DepartmentDto.class))
                .toList();
    }

    public EmployeeDto getDepartmentManager(int deptId) {
        Employee employee = departmentRepo.getDepartmentManager(deptId);
        if (employee != null)
            return EmployeeMapper.getInstance().mapEntityToDto(employee, EmployeeDto.class);
        return null;
    }

    public DepartmentDto getDepartmentByManagerID(int id) {
        Department department = departmentRepo.findDepartmentByManagerID(id);
        if (department == null)
            return null;
        return DepartmentMapper.getInstance().mapEntityToDto(department, DepartmentDto.class);
    }

    public List<DepartmentDto> getDepartmentsByManagerName(String name) {
        List<Department> departments = departmentRepo.findDepartmentsByManagerName(name);
        return departments.stream()
                .map(department -> DepartmentMapper.getInstance().mapEntityToDto(department, DepartmentDto.class))
                .toList();
    }

    public String createDepartment(DepartmentRequest departmentRequest) {
        String result = isDepartmentRequestValid(departmentRequest);
        if (!result.isEmpty()) {
            return result;
        } else {

            Employee employee = new EmployeeRepo(em).findById(Employee.class, departmentRequest.getDepMGRId());
            if (employee == null) {
                return "there is no Employee with this id";
            }
            Department department = new Department();
            department.setDepName(departmentRequest.getDepName());
            department.setDepMGR(employee);
            departmentRepo.save(department);
        }

        return "";
    }

    public DepartmentDto updateDepartmentName(int id, String name) {
        Department department = departmentRepo.findById(Department.class, id);
        if (department != null) {
            department.setDepName(name);
            departmentRepo.update(department);
            return DepartmentMapper.getInstance().mapEntityToDto(department, DepartmentDto.class);
        }
        return null;
    }

    public DepartmentDto updateDepartmentManager(int id, int mgrId) {
        Department department = departmentRepo.findById(Department.class, id);
        Employee employee = new EmployeeRepo(em).findById(Employee.class, mgrId);
        if (employee != null && department != null) {
            department.setDepMGR(employee);
            departmentRepo.update(department);
            return DepartmentMapper.getInstance().mapEntityToDto(department, DepartmentDto.class);
        }
        return null;
    }

    private String isDepartmentRequestValid(DepartmentRequest request) {
        String result = "";
        if (request.getDepName() == null || request.getDepName().length() > 45) {
            return "Department name is invalid";
        }

        if (request.getDepMGRId() == 0) {
            return "Invalid Manager ID";
        }
        return result;
    }

    public void deleteDepartmentById(int id) {
        departmentRepo.deleteById(Department.class, id);
    }

    public void deleteAllDepartments() {
        departmentRepo.deleteAll();
    }

}
