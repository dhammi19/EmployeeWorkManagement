package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.UsersModel;

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
            System.out.println("Error at getEmailAndFullNameOfUser(): "+e.getMessage());
        }

        return id;
    }
}
