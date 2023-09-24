package com.cybersoft.crm.payload;

public class ResponseData {
    private int status;
    private boolean success;
    private String description;

    public ResponseData() {}

    public ResponseData(int status, boolean success, String description) {
        this.status = status;
        this.success = success;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
