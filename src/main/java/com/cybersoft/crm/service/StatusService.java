package com.cybersoft.crm.service;

import com.cybersoft.crm.repository.StatusRepository;

public class StatusService {
    private StatusRepository statusRepository = new StatusRepository();

    public String getStatusNameByItsId(int id) {
        return statusRepository.getStatusNameByItsId(id);
    }
}
