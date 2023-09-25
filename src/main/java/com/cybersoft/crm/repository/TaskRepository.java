package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.TasksModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<TasksModel> getTasksByUserId(int id) {
        List<TasksModel> list = new ArrayList<>();

        try {
            String query = "select * from tasks where user_id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                TasksModel tasksModel = new TasksModel();
                tasksModel.setId(resultSet.getInt("id"));
                tasksModel.setName(resultSet.getString("name"));
                tasksModel.setStartDate(resultSet.getDate("start_date"));
                tasksModel.setEndDate(resultSet.getDate("end_date"));
                tasksModel.setUserId(resultSet.getInt("user_id"));
                tasksModel.setJobId(resultSet.getInt("job_id"));
                tasksModel.setStatusId(resultSet.getInt("status_id"));

                list.add(tasksModel);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getTasksByUserId(): "+e.getMessage());
        }

        return list;
    }

}
