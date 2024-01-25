package com.groupp.crystalweb.dto.request;

import java.time.LocalDate;
import java.util.List;

public record InventoryRequest(
        String name,
        Integer avaQuantity,
        Integer rop,
        LocalDate expDate,
        String supplierName,
        List<String> supplierPhone,
        String supplierEmail,
        String supplierNotes
) {
}