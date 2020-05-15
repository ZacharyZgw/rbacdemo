package com.dao;

import com.entity.UserRolePermission;

import java.io.Serializable;
import java.util.List;

public class JsonData<T> implements Serializable {
    private T data;
    private String message;
    private int status;
    private boolean success;

    public JsonData() {
        this.data = null;
        message = null;
        status = 0;
        success = false;
    }
    public JsonData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
