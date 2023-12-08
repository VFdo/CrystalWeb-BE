package com.groupp.crystalweb.dto.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int status;
    private String message;
    private int totalElements;
    private T payload;

    public ApiResponse(int status, String message, T payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public ApiResponse(int status, String message, T payload, int totalElements) {
        this.status = status;
        this.message = message;
        this.payload = payload;
        this.totalElements = totalElements;
    }
}
