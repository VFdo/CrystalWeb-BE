package com.groupp.crystalweb.exception;

import com.groupp.crystalweb.dto.response.ApiErrorResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ApiErrorResponse> handleException(Exception e) {
//        ApiErrorResponse apiError = new ApiErrorResponse(500, e.getMessage());
//        return ResponseEntity.status(apiError.getStatus()).body(apiError);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                400,
                "The following validations are not met",
                errors
        );
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        }
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                400,
                "The following validations are not met",
                errors
        );
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }
}

