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

    public TasksModel getTaskByItsId(int id) {
        TasksModel tasksModel = new TasksModel();

        try {
            String query = "select * from tasks where id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tasksModel.setId(resultSet.getInt("id"));
                tasksModel.setName(resultSet.getString("name"));
                tasksModel.setStartDate(resultSet.getDate("start_date"));
                tasksModel.setEndDate(resultSet.getDate("end_date"));
                tasksModel.setUserId(resultSet.getInt("user_id"));
                tasksModel.setJobId(resultSet.getInt("job_id"));
                tasksModel.setStatusId(resultSet.getInt("status_id"));
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getTaskByItsId(): "+e.getMessage());
        }

        return tasksModel;
    }

    public int updateTaskStatusById(int statusId, int id) {
        int rowsUpdated = 0;

        try {
            String query = "update tasks set status_id = ? where id = ?";

            Connection connection = MysqlConnection.getConnection(); // Lấy kết nối đến cơ sở dữ liệu

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, statusId); // Thiết lập giá trị cho tham số 1 (status_id)
            preparedStatement.setInt(2, id); // Thiết lập giá trị cho tham số 2 (id)

            rowsUpdated = preparedStatement.executeUpdate(); // Thực hiện cập nhật và lấy số hàng bị ảnh hưởng

            connection.close(); // Đóng kết nối sau khi hoàn thành

            return rowsUpdated; // Trả về số hàng bị cập nhật
        } catch (Exception e) {
            System.out.println("Error at updateTaskStatusById(): "+e.getMessage());
        }

        return rowsUpdated;
    }

    public int getTotalOfTasksByStatusId(int statusId) {
        int count = 0;

        try {
            String query = "select count(*) as total from tasks where status_id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, statusId);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                count = resultSet.getInt("total");
            }

            connection.close();
        } catch(Exception e) {
            System.out.println("Error at getTotalOfTasksByStatusId(): "+e.getMessage());
        }

        return count;
    }

    public int getAllTasksNumber() {
        int total = 0;

        try {
            String query = "select count(*) as total from tasks";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                total = resultSet.getInt("total");
            }

            connection.close();
        }catch (Exception e) {
            System.out.println("Error at getAllTasksNumber(): "+e.getMessage());
        }

        return total;
    }
}
