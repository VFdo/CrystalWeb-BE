package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Orderstatus;

import java.util.List;


public record OrderRequest (
        String clientRefId,
        List<String> items,
        Orderstatus orderStatus,
        String notes

){
}
