package com.soap_hrm.business.mappers;

import com.soap_hrm.business.dto.TimeEntryDto;
import com.soap_hrm.persistence.entities.TimeEntry;

public class TimeEntryMapper extends GenericMapping<TimeEntry, TimeEntryDto> {

    private static TimeEntryMapper instance;

    private TimeEntryMapper(){
        if (instance!=null){
            throw new RuntimeException("wld 3eeb");
        }
    }

    public static synchronized TimeEntryMapper getInstance(){
        if (instance == null){
            instance = new TimeEntryMapper();
        }
        return instance;
    }
}
