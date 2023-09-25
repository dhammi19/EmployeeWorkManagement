package com.cybersoft.crm.service;

import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.repository.TaskRepository;

import java.util.List;

public class TaskService {
    TaskRepository taskRepository = new TaskRepository();

    public List<TasksModel> getTasksByUserId(int id) {
//        for (TasksModel task : taskRepository.getTasksByUserId(id)) {
//            System.out.println(task.getId()+", "
//                    +task.getName()+", "
//                    +task.getStartDate()+", "
//                    +task.getEndDate()+", "
//                    +task.getJobId()+", "
//                    +task.getStatusId());
//        }
        return taskRepository.getTasksByUserId(id);
    }
}
