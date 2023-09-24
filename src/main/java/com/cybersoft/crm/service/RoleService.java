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
}
