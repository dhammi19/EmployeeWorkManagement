package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.UsersModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    public List<UsersModel> getUsersByEmailAndPassword(String email, String password) {
        List<UsersModel> list = new ArrayList<>();

        try {
            String query = "select * from users where email = ? and password = ?";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                UsersModel usersModel = new UsersModel();
                usersModel.setId(resultSet.getInt("id"));
                usersModel.setEmail(resultSet.getString("email"));
                usersModel.setFullName(resultSet.getString("fullname"));
                usersModel.setAvatar(resultSet.getString("avatar"));
                usersModel.setRoleId(resultSet.getInt("role_id"));

                list.add(usersModel);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getUsersByEmailAndPassword(): "+e.getMessage());
        }

        return list;
    }

    public UsersModel getEmailAndFullNameOfUser(int id) {
        UsersModel user = new UsersModel();

        try {
            String query = "select * from users where id = ?";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("fullname"));
                user.setAvatar(resultSet.getString("avatar"));
                user.setRoleId(resultSet.getInt("role_id"));
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getEmailAndFullNameOfUser(): "+e.getMessage());
        }

        return user;
    }

    public int getIdByUserEmail(String email) {
        int id = 0;
        try {
            String query = "select * from users where email = ?";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getIdByUserEmail(): "+e.getMessage());
        }

        return id;
    }

    public double getUnstartedTaskPercentage(int id) {
        double unstartedTaskPercentage = 0;
        double roundedPercentage = 0;
        try {
            String query = "SELECT (t1.task_count / t2.total_count)*100 AS task_ratio\n" +
                    "FROM (\n" +
                    "    SELECT COUNT(*) AS task_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE user_id = ? AND status_id = 1\n" +
                    ") AS t1,\n" +
                    "(\n" +
                    "    SELECT COUNT(*) AS total_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE user_id = ?\n" +
                    ") AS t2";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                unstartedTaskPercentage = resultSet.getDouble("task_ratio");
                BigDecimal bd = new BigDecimal(unstartedTaskPercentage);
                bd = bd.setScale(2, RoundingMode.HALF_UP); // Làm tròn và giữ lại 2 số thập phân
                roundedPercentage = bd.doubleValue();
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getUnstartedTaskPercentage(): "+e.getMessage());
        }

        return roundedPercentage;
    }

    public double getProcessingTaskPercentage(int id) {
        double processingTaskPercentage = 0;
        double roundedPercentage = 0;
        try {
            String query = "SELECT (t1.task_count / t2.total_count)*100 AS task_ratio\n" +
                    "FROM (\n" +
                    "    SELECT COUNT(*) AS task_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE user_id = ? AND status_id = 2\n" +
                    ") AS t1,\n" +
                    "(\n" +
                    "    SELECT COUNT(*) AS total_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE user_id = ?\n" +
                    ") AS t2";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                processingTaskPercentage = resultSet.getDouble("task_ratio");
                BigDecimal bd = new BigDecimal(processingTaskPercentage);
                bd = bd.setScale(2, RoundingMode.HALF_UP); // Làm tròn và giữ lại 2 số thập phân
                roundedPercentage = bd.doubleValue();
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getProcessingTaskPercentage(): "+e.getMessage());
        }

        return roundedPercentage;
    }

    public double getCompletedTaskPercentage(int id) {
        double completedTaskPercentage = 0;
        double roundedPercentage = 0;
        try {
            String query = "SELECT (t1.task_count / t2.total_count)*100 AS task_ratio\n" +
                    "FROM (\n" +
                    "    SELECT COUNT(*) AS task_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE user_id = ? AND status_id = 3\n" +
                    ") AS t1,\n" +
                    "(\n" +
                    "    SELECT COUNT(*) AS total_count\n" +
                    "    FROM tasks\n" +
                    "    WHERE user_id = ?\n" +
                    ") AS t2";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                completedTaskPercentage = resultSet.getDouble("task_ratio");
                BigDecimal bd = new BigDecimal(completedTaskPercentage);
                bd = bd.setScale(2, RoundingMode.HALF_UP); // Làm tròn và giữ lại 2 số thập phân
                roundedPercentage = bd.doubleValue();
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getCompletedTaskPercentage(): "+e.getMessage());
        }

        return roundedPercentage;
    }


}
