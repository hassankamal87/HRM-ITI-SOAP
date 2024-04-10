package com.soap_hrm.business.service;

import com.soap_hrm.business.dto.JobDto;
import com.soap_hrm.business.mappers.JobMapper;
import com.soap_hrm.persistence.connection.JPAManager;
import com.soap_hrm.persistence.entities.Job;
import com.soap_hrm.persistence.repo.JobRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;

import java.util.List;

public class JobService {
    EntityManager em;
    JobRepo jobRepo;

    public JobService() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        jobRepo = new JobRepo(em);
    }


    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepo.findAll(Job.class);
        return jobs.stream()
                .map(job -> JobMapper.getInstance().mapEntityToDto(job, JobDto.class))
                .toList();
    }

    public JobDto getJobById(int id) {
        Job job = jobRepo.findById(Job.class, id);
        if (job != null)
            return JobMapper.getInstance().mapEntityToDto(job, JobDto.class);
        return null;
    }


    public JobDto createJob(JobDto jobDto) {
        if (jobDto.getJobTitle().isEmpty())
            return null;
        Job newJob = new Job();
        newJob.setJobTitle(jobDto.getJobTitle());
        try {
            newJob = jobRepo.save(newJob);
            jobDto.setId(newJob.getId());
            return jobDto;
        } catch (PersistenceException e) {
            return null;
        }
    }

    public boolean deleteJobById(int id) {
        try {
            jobRepo.deleteById(Job.class, id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
