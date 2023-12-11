package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Orderstatus;


public record OrderRequest (
        String refId,
        String clientRefId,
        float totalPrice,
        Orderstatus orderStatus,
        String notes

){
}
