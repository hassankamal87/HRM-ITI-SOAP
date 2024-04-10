package com.soap_hrm.presentation.controller;

import com.soap_hrm.business.dto.HolidayDto;
import com.soap_hrm.business.dto.requests_dtos.HolidayRequest;
import com.soap_hrm.business.service.HolidayService;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebService
public class HolidayController {
    HolidayService holidayService;

    public HolidayController() {
        holidayService = new HolidayService();
    }

    public List<HolidayDto> getAllHolidays() {
        return holidayService.getAllHolidays();
    }

    public HolidayDto getHolidayById(@WebParam(name = "holidayId") int id) {
        HolidayDto holidayDto = holidayService.getHolidayById(id);
        if (holidayDto != null)
            return holidayDto;
        else {
            throwSoapFault("invalid holiday id");
            return null;
        }
    }

    public List<HolidayDto> getHolidayForEmployeeUsingId(@WebParam(name = "empId") int id) {
        return holidayService.getHolidaysForEmployee(id);
    }

    public List<HolidayDto> getHolidayForEmployeeUsingDate(@WebParam(name = "date") String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        return holidayService.getHolidaysByDate(date);
    }

    public HolidayDto createHoliday(HolidayRequest holidayRequest) {
        if (holidayRequest == null) {
            throwSoapFault("there is no body");
            return null;
        }
        HolidayDto holidayDto = holidayService.createHoliday(holidayRequest);
        if (holidayDto != null)
            return holidayDto;
        throwSoapFault("invalid holiday data");
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
