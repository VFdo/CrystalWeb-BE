package com.groupp.crystalweb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfo {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
