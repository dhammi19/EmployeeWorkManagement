package com.cybersoft.crm.service;

import com.cybersoft.crm.model.StatusModel;
import com.cybersoft.crm.repository.StatusRepository;

import java.util.ArrayList;
import java.util.List;

public class StatusService {
    private StatusRepository statusRepository = new StatusRepository();

    public String getStatusNameByItsId(int id) {
        return statusRepository.getStatusNameByItsId(id);
    }

    public List<StatusModel> getAllStatus() {
        return statusRepository.getAllStatus();
    }

    public List<StatusModel> getAllStatusExpectItsId(List<StatusModel> list, int id) {
        List<StatusModel> statusList = new ArrayList<>();

        for (StatusModel statusModel : list) {
            if (statusModel.getId() == id) {
                continue;
            } else {
                statusList.add(new StatusModel(statusModel.getId(), statusModel.getName()));
            }
        }

        return statusList;
    }
}
