package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.StatusModel;
import com.cybersoft.crm.model.TasksModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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

    public List<TasksModel> getTasksByUserAndStatusId(int userId, int statusId) {
        List<TasksModel> list = new ArrayList<>();

        try {
            String query = "select * from tasks where user_id = ? and status_id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, statusId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
        } catch(Exception e) {
            System.out.println("Error at getTasksByUserAndStatusId(): "+e.getMessage());
        }

        return list;
    }

    public double getTaskPercentageByJobAndStatusId(int jobId, int statusId) {
        double completedTaskPercentage = 0;
        double roundedPercentage = 0;

        try {
            String query = "SELECT (t1.task_count / t2.total_count)*100 AS task_ratio\n" +
                    "FROM (\n" +
                    "    SELECT COUNT(*) AS task_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE job_id = ? AND status_id = ?\n" +
                    ") AS t1,\n" +
                    "(\n" +
                    "    SELECT COUNT(*) AS total_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE job_id = ?\n" +
                    ") AS t2";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, jobId);
            preparedStatement.setInt(2, statusId);
            preparedStatement.setInt(3, jobId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                completedTaskPercentage = resultSet.getDouble("task_ratio");
                BigDecimal bd = new BigDecimal(completedTaskPercentage);
                bd = bd.setScale(2, RoundingMode.HALF_UP); // Làm tròn và giữ lại 2 số thập phân
                roundedPercentage = bd.doubleValue();
            }

            connection.close();
        } catch(Exception e) {
            System.out.println("Error at getTaskPercentageByJobAndStatusId(): "+e.getMessage());
        }

        return roundedPercentage;
    }

    public int getTotalTaskOfJobByJobId(int id) {
        int count = 0;

        try {
            String query = "select count(*) as total from tasks where job_id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at etTotalTaskOfJobByJobId(): "+e.getMessage());
        }

        return count;
    }

    public List<TasksModel> getTasksByJobId(int id) {
        List<TasksModel> list = new ArrayList<>();

        try {
            String query = "select * from tasks where job_id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
            System.out.println("Error at getTasksByJobId(): "+e.getMessage());
        }

        return list;
    }

    public List<TasksModel> getAllTasks() {
        List<TasksModel> list = new ArrayList<>();

        try {
            String query = "select * from tasks";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);

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
            System.out.println("Error at getAllTasks(): "+e.getMessage());
        }

        return list;
    }

    public int addTask(String taskName, Date startDate, Date endDate, int userId, int jobId, int statusId) {
        int rowsEffected = 0;

        try {
            String query = "insert into tasks(name, start_date, end_date, user_id, job_id, status_id) values(?, ?, ?, ?, ?, ?)";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, taskName);
            preparedStatement.setDate(2, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, jobId);
            preparedStatement.setInt(6, statusId);

            rowsEffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at addTask(): "+e.getMessage());
        }

        return rowsEffected;
    }

    public int updateTaskById(String name, int jobId, int userId, Date startDate, Date endDate, int statusId, int id) {
        int rowsEffected = 0;
        try {
            String query = "update tasks " +
                    "set name = ?, " +
                    "job_id = ?, " +
                    "user_id = ?, " +
                    "start_date = ?, " +
                    "end_date = ?, " +
                    "status_id = ? " +
                    "where id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, jobId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setDate(4, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(5, new java.sql.Date(endDate.getTime()));
            preparedStatement.setInt(6, statusId);
            preparedStatement.setInt(7, id);

            rowsEffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at updateTaskById(): "+e.getMessage());
        }

        return rowsEffected;
    }

    public int deleteTaskById(int id) {
        int rowsEffected = 0;

        try {
            String query = "delete from tasks where id = ?";

            connection = MysqlConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            rowsEffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at deleteTaskById(): "+e.getMessage());
        }

        return rowsEffected;
    }
}
