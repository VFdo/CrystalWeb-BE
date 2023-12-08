package com.groupp.crystalweb.exception;

import com.groupp.crystalweb.dto.response.ApiErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
        ApiErrorResponse apiError = new ApiErrorResponse(500, e.getMessage());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}

