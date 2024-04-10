package com.soap_hrm.presentation.controller;

import com.soap_hrm.business.dto.JobDto;
import com.soap_hrm.business.service.JobService;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.util.List;


@WebService
public class JobController {
    JobService jobService;

    public JobController() {
        jobService = new JobService();
    }

    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }

    public JobDto getJobById(@WebParam(name = "jobId") int id) {
        JobDto jobDto = jobService.getJobById(id);
        if (jobDto != null)
            return jobDto;
        else {
            throwSoapFault("Invalid Job ID");
            return null;
        }
    }

    public JobDto createJob(@WebParam(name = "JobData")JobDto jobDto) {
        if (jobDto == null) {
            throwSoapFault("there is no body");
            return null;
        }
        JobDto createdJobDto = jobService.createJob(jobDto);
        if (createdJobDto != null){
            return createdJobDto;
        }else {
            throwSoapFault("invalid job data");
            return null;
        }
    }

    public void deleteJobById(@WebParam(name = "jobId") int id) {
        boolean result = jobService.deleteJobById(id);
        if (!result)
            throwSoapFault("this job is already used, can't delete it now");
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
