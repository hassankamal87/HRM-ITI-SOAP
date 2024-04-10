package com.soap_hrm.business.service;

import com.soap_hrm.business.dto.TimeEntryDto;
import com.soap_hrm.business.dto.requests_dtos.TimeEntryRequest;
import com.soap_hrm.business.mappers.TimeEntryMapper;
import com.soap_hrm.persistence.connection.JPAManager;
import com.soap_hrm.persistence.entities.Employee;
import com.soap_hrm.persistence.entities.TimeEntry;
import com.soap_hrm.persistence.repo.EmployeeRepo;
import com.soap_hrm.persistence.repo.TimeEntryRepo;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class TimeEntryService {
    EntityManager em;
    TimeEntryRepo timeEntryRepo;

    public TimeEntryService() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        timeEntryRepo = new TimeEntryRepo(em);
    }

    public List<TimeEntryDto> getAllTimeEntries() {
        List<TimeEntry> timeEntries = timeEntryRepo.findAll(TimeEntry.class);
        return timeEntries.stream()
                .map(timeEntry -> TimeEntryMapper.getInstance().mapEntityToDto(timeEntry, TimeEntryDto.class)).toList();
    }

    public List<TimeEntryDto> getTimeEntriesForEmployee(int id) {
        List<TimeEntry> timeEntries = timeEntryRepo.findTimeEntriesForEmployee(id);
        return timeEntries.stream()
                .map(timeEntry -> TimeEntryMapper.getInstance().mapEntityToDto(timeEntry, TimeEntryDto.class)).toList();
    }

    public List<TimeEntryDto> getTimeEntriesForEmployeesInMonth(int id, LocalDate date) {
        List<TimeEntry> timeEntries = timeEntryRepo.findTimeEntriesForEmployeeInMonth(id, date.getYear(), date.getMonthValue());
        return timeEntries.stream()
                .map(timeEntry -> TimeEntryMapper.getInstance().mapEntityToDto(timeEntry, TimeEntryDto.class)).toList();
    }

    public Long getTotalHoursForEmployeeInMonth(int id, LocalDate date) {
        return timeEntryRepo.findTotalHoursForEmployeeInMonth(id, date.getYear(), date.getMonthValue());
    }

    public TimeEntryDto createTimeEntry(TimeEntryRequest request){
        String result = isTimeEntryRequestValid(request);
        if (!result.isEmpty())
            return null;

        Employee employee = new EmployeeRepo(em).findById(Employee.class, request.getEmpId());
        if (employee == null)
            return null;

        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setEntryDate(request.getEntryDate());
        timeEntry.setTimeIn(request.getTimeIn());
        timeEntry.setTimeOut(request.getTimeOut());
        timeEntry.setEmployee(employee);

        timeEntry = timeEntryRepo.save(timeEntry);

        return TimeEntryMapper.getInstance().mapEntityToDto(timeEntry, TimeEntryDto.class);

    }

    private String isTimeEntryRequestValid(TimeEntryRequest request) {
        if (request.getEntryDate() == null)
            return "invalid entry date";

        if (request.getTimeIn() == null)
            return "invalid time in";

        if (request.getTimeOut() == null)
            return "invalid time out";
        if (request.getEmpId() == 0)
            return "invalid Employee Id";
        return "";
    }
}
