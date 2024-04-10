package com.soap_hrm.business.mappers;

import com.soap_hrm.business.dto.JobDto;
import com.soap_hrm.persistence.entities.Job;

public class JobMapper extends GenericMapping<Job, JobDto> {

    private static JobMapper instance;

    private JobMapper(){
        if (instance!=null){
            throw new RuntimeException("wld 3eeb");
        }
    }

    public static synchronized JobMapper getInstance(){
        if (instance == null){
            instance = new JobMapper();
        }
        return instance;
    }
}
