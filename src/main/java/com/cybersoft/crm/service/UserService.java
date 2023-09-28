package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.UsersRepository;

import java.util.List;

public class UserService {
    UsersRepository usersRepository = new UsersRepository();

    public boolean checkLogin(String email, String password) {
        List<UsersModel> list = usersRepository.getUsersByEmailAndPassword(email, password);

        for(UsersModel usersModel: list) {
            // System.out.println(usersModel.getFullName());
        }

        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public UsersModel getEmailAndFullNameOfUserService(int id) {
        return usersRepository.getEmailAndFullNameOfUser(id);
    }

    public int getIdByUserEmail(String email) {
        return usersRepository.getIdByUserEmail(email);
    }

    public double getUnstartedTaskPercentage(int id) {
        return usersRepository.getUnstartedTaskPercentage(id);
    }

    public double getProcessingTaskPercentage(int id) {
        return usersRepository.getProcessingTaskPercentage(id);
    }

    public double getCompletedTaskPercentage(int id) {
        return usersRepository.getCompletedTaskPercentage(id);
    }

    public String getUserNameByUserId(int id) {
        return usersRepository.getUserNameByUserId(id);
    }

    public List<UsersModel> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    public boolean checkAddUser(String email, String password, String fullName, int roleId) {
        boolean result = false;
        int numberOfRowEffected = usersRepository.addUser(email, password, fullName, roleId);

        if (numberOfRowEffected > 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}
