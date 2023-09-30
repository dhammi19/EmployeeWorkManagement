package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.JobsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
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

    public List<JobsModel> getAllJobs() {
        List<JobsModel> list = new ArrayList<>();

        try {
            String query = "select * from jobs";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                JobsModel jobsModel =new JobsModel();
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStartDate(resultSet.getDate("start_date"));
                jobsModel.setEndDate(resultSet.getDate("end_date"));

                list.add(jobsModel);
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getAllJobs(): "+e.getMessage());
        }

        return list;
    }

    public int addJob(String jobName, Date startDate, Date endDate) {
        int rowsEffected = 0;

        try {
            String query = "insert into jobs(name, start_date, end_date) values(?, ?, ?)";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, jobName);
            preparedStatement.setDate(2, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));
            //preparedStatement.setDate(2, (java.sql.Date) startDate);
            //preparedStatement.setDate(3, (java.sql.Date) endDate);

            rowsEffected = preparedStatement.executeUpdate();

            connection.close();
        } catch(Exception e) {
            System.out.println("Error at addJob(): "+e.getMessage());
        }

        return rowsEffected;
    }

    public JobsModel getJobById(int id) {
        JobsModel jobsModel =new JobsModel();
        try {
            String query = "select * from jobs where id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                jobsModel.setId(resultSet.getInt("id"));
                jobsModel.setName(resultSet.getString("name"));
                jobsModel.setStartDate(resultSet.getDate("start_date"));
                jobsModel.setEndDate(resultSet.getDate("end_date"));
            }

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at getJobById(): "+e.getMessage());
        }

        return jobsModel;
    }

    public int updateJobById(String name, Date startDate, Date endDate, int id) {
        int rowsEffected = 0;

        try {
            String query = "update jobs set name = ?, start_date = ?, end_date = ? where id = ?";

            connection = MysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));
            preparedStatement.setInt(4, id);

            rowsEffected = preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error at updateJobById(): "+e.getMessage());
        }

        return rowsEffected;
    }
}
