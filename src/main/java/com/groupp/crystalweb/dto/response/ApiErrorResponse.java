package com.groupp.crystalweb.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ApiErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String message;
    private Map<String, String> errorMap;

    public ApiErrorResponse(int status, String message) {
        this.timeStamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }

    public ApiErrorResponse(int status, String message, Map<String, String> errorMap) {
        this.timeStamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.errorMap = errorMap;
    }
}

