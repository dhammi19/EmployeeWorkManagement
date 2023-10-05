package com.cybersoft.crm.service;

import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.repository.JobsRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobService {
    private JobsRepository jobsRepository = new JobsRepository();

    public String getJobNameByItsId(int id) {
        return jobsRepository.getJobNameByItsId(id);
    }

    public List<JobsModel> getAllJobs() {
        return jobsRepository.getAllJobs();
    }

    public boolean isJobAdded(String jobName, String startDate, String endDate) {
        boolean isAdded = false;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date convertedStartDate = dateFormat.parse(startDate);
            Date convertedEndDate = dateFormat.parse(endDate);

            int rowsEffected = jobsRepository.addJob(jobName, convertedStartDate, convertedEndDate);

            if(rowsEffected > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isAdded;
    }

    public JobsModel getJobById(int id) {
        return jobsRepository.getJobById(id);
    }

    public boolean editJob(String name, String startDate, String endDate, int id) {
        boolean isAdded = false;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date convertedStartDate = dateFormat.parse(startDate);
            Date convertedEndDate = dateFormat.parse(endDate);

            int rowsEffected = jobsRepository.updateJobById(name, convertedStartDate, convertedEndDate, id);

            if(rowsEffected > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isAdded;
    }

    public boolean isJobDeleted(int id) {
        int rowsEffected = jobsRepository.deleteJobsById(id);

        if(rowsEffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<JobsModel> excludeJobById(List<JobsModel> list, int id) {
        List<JobsModel> jobList = new ArrayList<>();

        for (JobsModel jobsModel : list) {
            if (jobsModel.getId() == id) {
                continue;
            } else {
                jobList.add(new JobsModel(jobsModel.getId(), jobsModel.getName(), jobsModel.getStartDate(), jobsModel.getEndDate()));
            }
        }

        return jobList;
    }
}
