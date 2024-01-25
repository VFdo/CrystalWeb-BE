package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Category;
import com.groupp.crystalweb.entity.Inventory;

import java.time.LocalDate;
import java.util.List;

public record InventoryRequest(
        String name,
        Category category,
        String unitOfMeasure,
        Integer avaQuantity,
        Integer rop,
        Float unitPrice,
        Inventory.InventoryStatus status,
        LocalDate expDate,
        String supplierName,
        List<String> supplierPhone,
        String supplierEmail,
        String supplierNotes
) {
}