package com.groupp.crystalweb.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
    private int status;
    private String message;
    private PageInfo pageInfo;
    private Object payload;

    public ApiResponse(int status, String message, Object payload) {
        this.status = status;
        this.message = message;
        this.payload = payload;
    }

    public ApiResponse(int status, String message, Object payload, PageInfo pageInfo) {
        this.status = status;
        this.message = message;
        this.payload = payload;
        this.pageInfo = pageInfo;
    }
}
