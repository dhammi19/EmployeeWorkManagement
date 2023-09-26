package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.StatusModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<StatusModel> getAllStatus() {
        List<StatusModel> list = new ArrayList<>();

        try {
            String query = "select * from status";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                StatusModel statusModel = new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));

                list.add(statusModel);
            }

            connection.close();
        } catch(Exception e) {
            System.out.println("Error at getAllStatus(): "+e.getMessage());
        }

        return list;
    }
}
