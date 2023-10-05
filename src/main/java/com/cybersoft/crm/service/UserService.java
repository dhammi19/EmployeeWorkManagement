package com.cybersoft.crm.service;

import com.cybersoft.crm.model.UsersModel;
import com.cybersoft.crm.repository.UsersRepository;

import java.util.ArrayList;
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

    public UsersModel getUserByUserId(int id) {
        return usersRepository.getEmailAndFullNameOfUser(id);
    }

    public boolean isEmailExists(String email) {
        return usersRepository.isEmailExists(email);
    }

    public boolean checkUpdated(int id, String fullName, String password, int roleId) {
        if (usersRepository.updateUser(id, fullName, password, roleId) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUserById(int id) {
        int checkDelete = usersRepository.deleteUserById(id);

        if (checkDelete > 0) {
            return true;
        } else {
            return false;
        }
    }

    public double getTaskPercentageByUserAndStatusId(int userId, int statusId) {
        return usersRepository.getTaskPercentageByUserAndStatusId(userId, statusId);
    }

    public List<UsersModel> excludeUserById(List<UsersModel> list, int id) {
        List<UsersModel> userList = new ArrayList<>();

        for(UsersModel usersModel : list) {
            if(usersModel.getId() == id) {
                continue;
            } else {
                userList.add(new UsersModel(
                        usersModel.getId(),
                        usersModel.getEmail(),
                        usersModel.getPassword(),
                        usersModel.getFullName(),
                        usersModel.getAvatar(),
                        usersModel.getRoleId())
                );
            }
        }

        return userList;
    }
}
