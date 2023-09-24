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
}
