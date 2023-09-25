package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobsRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public String getJobNameByItsId(int id) {
        String jobName = "";

        try {
            String query = "select name from jobs where id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                jobName = resultSet.getString("name");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getJobNameByItsId(): "+e.getMessage());
        }

        return jobName;
    }
}
