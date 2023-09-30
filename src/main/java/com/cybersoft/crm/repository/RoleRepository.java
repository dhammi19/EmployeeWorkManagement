package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> getRoles() {
        List<RoleModel> list = new ArrayList<>();

        try {
            String query = "select * from roles";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                RoleModel roleModel = new RoleModel();
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));

                list.add(roleModel);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getRoles(): "+e.getMessage());
        }

        return list;
    }

    public int deleteRoleById(int id) {
        int result = 0;
        try {
            String query = "delete from roles where id = ?";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            result = preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at deleteRoleById(): "+e.getMessage());
        }

        return result;
    }

    public String getRoleNameByRoleId(int id) {
        String roleName = "";

        try {
            String query = "select name from roles where id = ?";

            Connection connection = MysqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                roleName = resultSet.getString("name");
            }

            connection.close();
        } catch(Exception e) {
            System.out.println("Error at getRoleNameByRoleId(): "+e.getMessage());
        }

        return roleName;
    }

    public int addRole(String name, String description) {
        int numberRowsEffected = 0;

        try {
            String query = "insert into roles(name, description) values (?, ?)";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);

            numberRowsEffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at addRole(): "+e.getMessage());
        }

        return numberRowsEffected;
    }

    public int updateRoleById(String name, String description, int id) {
        int numberRowsEffected = 0;
        try {
            String query = "update roles set name = ?, description = ? where id = ?";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);

            numberRowsEffected = preparedStatement.executeUpdate();

            connection.close();

        } catch (Exception e) {
            System.out.println("Error at updateRoleById(): "+e.getMessage());
        }

        return numberRowsEffected;
    }

    public RoleModel getRoleById(int id) {
        RoleModel roleModel = new RoleModel();

        try {
            String query = "select * from roles where id = ?";

            Connection connection = MysqlConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));
            }

            connection.close();
        } catch(Exception e) {
            System.out.println("Error at getRoleById(): "+e.getMessage());
        }

        return roleModel;
    }
}
