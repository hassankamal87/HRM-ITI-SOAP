package com.soap_hrm.business.service;

import com.soap_hrm.business.dto.HolidayDto;
import com.soap_hrm.business.dto.requests_dtos.HolidayRequest;
import com.soap_hrm.business.mappers.HolidayMapper;
import com.soap_hrm.persistence.connection.JPAManager;
import com.soap_hrm.persistence.entities.Employee;
import com.soap_hrm.persistence.entities.Holiday;
import com.soap_hrm.persistence.repo.EmployeeRepo;
import com.soap_hrm.persistence.repo.HolidayRepo;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class HolidayService {

    EntityManager em;
    HolidayRepo holidayRepo;

    public HolidayService(){
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        holidayRepo = new HolidayRepo(em);
    }

    public List<HolidayDto> getAllHolidays(){
        List<Holiday> holidays = holidayRepo.findAll(Holiday.class);
        return holidays.stream()
                .map(holiday -> HolidayMapper.getInstance().mapEntityToDto(holiday, HolidayDto.class)).toList();
    }

    public HolidayDto getHolidayById(int id){
        Holiday holiday = holidayRepo.findById(Holiday.class, id);
        if (holiday != null)
            return HolidayMapper.getInstance().mapEntityToDto(holiday, HolidayDto.class);
        return null;
    }

    public List<HolidayDto> getHolidaysForEmployee(int id){
        List<Holiday> holidays = holidayRepo.findHolidaysForEmployee(id);
        return holidays.stream()
                .map(holiday -> HolidayMapper.getInstance().mapEntityToDto(holiday, HolidayDto.class)).toList();
    }

    public List<HolidayDto> getHolidaysByDate(LocalDate date){
        List<Holiday> holidays = holidayRepo.findHolidaysInDay(date);
        return holidays.stream()
                .map(holiday -> HolidayMapper.getInstance().mapEntityToDto(holiday, HolidayDto.class)).toList();
    }

    public HolidayDto createHoliday(HolidayRequest holidayRequest){
        String result = isRequestValid(holidayRequest);
        if (!result.isEmpty())
            return null;
        EmployeeRepo employeeRepo = new EmployeeRepo(em);
        Employee employee = employeeRepo.findById(Employee.class,holidayRequest.getEmployeeId());
        if (employee == null || employee.getAnnualHolidays() < 1) {
            return null;
        }
        Holiday newHoliday = new Holiday();
        newHoliday.setHolidayDate(holidayRequest.getHolidayDate());
        newHoliday.setDescription(holidayRequest.getDescription());
        newHoliday.setEmployee(employee);

        newHoliday = holidayRepo.save(newHoliday);
        employee.decrementAnnualHoliday();
        employeeRepo.update(employee);
        return HolidayMapper.getInstance().mapEntityToDto(newHoliday, HolidayDto.class);
    }

    private String isRequestValid(HolidayRequest holidayRequest) {
        if (holidayRequest.getEmployeeId() == 0)
            return "invalid number";
        if (holidayRequest.getHolidayDate() == null)
            return "invalid date";
        if (holidayRequest.getDescription().isEmpty())
            return "invalid description";
        return "";
    }
}
