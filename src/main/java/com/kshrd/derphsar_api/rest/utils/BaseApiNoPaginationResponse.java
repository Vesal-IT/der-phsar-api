package com.kshrd.derphsar_api.rest.utils;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class BaseApiNoPaginationResponse<T> {
    private String message;
    private T data;
    private HttpStatus status;
    private Timestamp time;

    public BaseApiNoPaginationResponse(){}

    public BaseApiNoPaginationResponse(String message, T data, HttpStatus status, Timestamp time) {
        this.message = message;
        this.data = data;
        this.status = status;
        this.time = time;
    }

    @Override
    public String toString() {
        return "BaseApiNoPaginationResponse{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", time=" + time +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
