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

    public TasksModel getTaskByItsId(int id) {
        return taskRepository.getTaskByItsId(id);
    }

    public boolean checkTaskStatusUpdate(int statusId, int id) {
        boolean isUpdated;

        int result = taskRepository.updateTaskStatusById(statusId, id);

        if(result > 0) {
            isUpdated = true;
        } else {
            isUpdated = false;
        }

        return isUpdated;
    }

    public int getTotalOfTasksByStatusId(int statusId) {
        return taskRepository.getTotalOfTasksByStatusId(statusId);
    }

    public int getAllTasksNumber() {
        return taskRepository.getAllTasksNumber();
    }

    public List<TasksModel> getAllTasks(int userId, int statusId) {
        return taskRepository.getTasksByUserAndStatusId(userId, statusId);
    }
}
