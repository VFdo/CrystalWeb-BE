package com.groupp.crystalweb.dto.request;

import java.util.Date;

public record InventoryRequest(
        String refId,
        String name,
        Integer avaQuantity,
        Integer rop,
        Date expDate,
        String supInfo
) {
}