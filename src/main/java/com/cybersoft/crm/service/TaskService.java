package com.cybersoft.crm.service;

import com.cybersoft.crm.model.TasksModel;
import com.cybersoft.crm.repository.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    public double getTaskPercentageByJobAndStatusId(int jobId, int statusId) {
        return taskRepository.getTaskPercentageByJobAndStatusId(jobId, statusId);
    }

    public int getTasksOfJob(int id) {
        return taskRepository.getTotalTaskOfJobByJobId(id);
    }

    public List<TasksModel> showTasksOfJob(int id) {
        return taskRepository.getTasksByJobId(id);
    }

    public List<TasksModel> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public boolean isTaskAdded(String taskName, String startDate, String endDate, int userId, int jobId, int statusId) {
        boolean isAdded = false;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date convertedStartDate = dateFormat.parse(startDate);
            Date convertedEndDate = dateFormat.parse(endDate);

            int rowsEffected = taskRepository.addTask(taskName, convertedStartDate, convertedEndDate, userId, jobId, statusId);

            if(rowsEffected > 0) {
                isAdded = true;
            } else {
                isAdded = false;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return isAdded;
    }

    public boolean isTaskUpdated(String name, int jobId, int userId, String startDate, String endDate, int statusId, int id) {
        boolean isUpdated = false;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date convertedStartDate = dateFormat.parse(startDate);
            Date convertedEndDate = dateFormat.parse(endDate);

            int rowsEffected = taskRepository.updateTaskById(name, jobId, userId, convertedStartDate, convertedEndDate, statusId, id);

            if(rowsEffected > 0) {
                isUpdated = true;
            } else {
                isUpdated = false;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return isUpdated;
    }

    public boolean isTaskDeleted(int id) {
        boolean isDeleted = false;
        int rowsEffected = taskRepository.deleteTaskById(id);

        if (rowsEffected > 0) {
            isDeleted = true;
        } else {
            isDeleted = false;
        }

        return isDeleted;
    }

}
