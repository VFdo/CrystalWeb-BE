package com.groupp.crystalweb.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String message;

    public ApiErrorResponse(int status, String message) {
        this.timeStamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
    }
}

