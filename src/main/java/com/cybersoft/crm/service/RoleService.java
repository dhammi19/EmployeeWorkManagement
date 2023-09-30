package com.cybersoft.crm.service;

import com.cybersoft.crm.model.RoleModel;
import com.cybersoft.crm.repository.RoleRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getAllRoles() {
        return roleRepository.getRoles();
    }

    public boolean deleteRoleById(int id) {
        int result = roleRepository.deleteRoleById(id);

        return result > 0 ? true : false;
    }

    public String getRoleNameByRoleId(int id) {
        return roleRepository.getRoleNameByRoleId(id);
    }

    public boolean addRole(String name, String description) {
        int isUpdated = roleRepository.addRole(name, description);
        if (isUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editRole(String name, String description, int id) {
        int isUpdated = roleRepository.updateRoleById(name, description, id);
        if (isUpdated > 0) {
            return true;
        } else {
            return false;
        }
    }

    public RoleModel getRoleById(int id) {
        RoleModel roleModel = roleRepository.getRoleById(id);
//        System.out.println("Id: "+roleModel.getId()+", Name: "+roleModel.getName()+", Description: "+roleModel.getDescription());
        return roleModel;
    }
}
