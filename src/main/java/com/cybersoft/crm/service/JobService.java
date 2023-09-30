package com.cybersoft.crm.service;

import com.cybersoft.crm.repository.JobsRepository;

public class JobService {
    private JobsRepository jobsRepository = new JobsRepository();

    public String getJobNameByItsId(int id) {
        return jobsRepository.getJobNameByItsId(id);
    }


}
