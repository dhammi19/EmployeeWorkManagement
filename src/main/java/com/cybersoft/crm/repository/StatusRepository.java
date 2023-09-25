package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatusRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public String getStatusNameByItsId(int id) {
        String statusName = "";

        try {
            String query = "select name from status where id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                statusName = resultSet.getString("name");
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getStatusNameByItsId(): "+e.getMessage());
        }

        return statusName;
    }
}
