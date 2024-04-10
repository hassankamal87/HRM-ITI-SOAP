package com.soap_hrm.business.mappers;

import com.soap_hrm.business.dto.HolidayDto;
import com.soap_hrm.persistence.entities.Holiday;

public class HolidayMapper extends GenericMapping<Holiday, HolidayDto> {

    private static HolidayMapper instance;

    private HolidayMapper(){
        if (instance!=null){
            throw new RuntimeException("wld 3eeb");
        }
    }

    public static synchronized HolidayMapper getInstance(){
        if (instance == null){
            instance = new HolidayMapper();
        }
        return instance;
    }
}
