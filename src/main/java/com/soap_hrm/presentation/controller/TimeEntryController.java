package com.soap_hrm.presentation.controller;

import com.soap_hrm.business.dto.TimeEntryDto;
import com.soap_hrm.business.dto.requests_dtos.TimeEntryRequest;
import com.soap_hrm.business.service.TimeEntryService;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@WebService
public class TimeEntryController {
    TimeEntryService timeEntryService;

    public TimeEntryController() {
        timeEntryService = new TimeEntryService();
    }

    public List<TimeEntryDto> getAllTimeEntries() {
        return timeEntryService.getAllTimeEntries();
    }

    public List<TimeEntryDto> getTimeEntriesForEmployee(@WebParam(name = "empId") int id) {
        return timeEntryService.getTimeEntriesForEmployee(id);
    }

    public List<TimeEntryDto> getTimeEntriesForEmployeesInMonth(@WebParam(name = "empId") int id, @WebParam(name = "date") String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        return timeEntryService.getTimeEntriesForEmployeesInMonth(id, date);
    }

    public Long getTotalHoursForEmployeeInMonth(@WebParam(name = "empId") int id, @WebParam(name = "date") String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        Long totalHoursForEmployeeInMonth = timeEntryService.getTotalHoursForEmployeeInMonth(id, date);
        return Objects.requireNonNullElse(totalHoursForEmployeeInMonth, 0L);
    }

    public TimeEntryDto createTimeEntry(TimeEntryRequest timeEntryRequest){
        if (timeEntryRequest == null){
            throwSoapFault("there is no body");
            return null;
        }

        TimeEntryDto dto = timeEntryService.createTimeEntry(timeEntryRequest);
        if (dto != null)
            return dto;
        throwSoapFault("invalid data");
        return null;
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