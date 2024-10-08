package com.onefineday.manage.dto;


import java.util.List;

public class ApiResponse<T> {

    private T data;
    private boolean success;
    private List<String> errors;

    public ApiResponse() {}

    public ApiResponse(T data, boolean success, List<String> errors) {
        this.data = data;
        this.success = success;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}