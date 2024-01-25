package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Status;
import jakarta.persistence.Id;

import java.util.Date;

public record PetstoreRequest(
        String name,
        String refId,
        String descrption,
        float unitprice,
        Integer productid,
        Status catergory,
        byte[] photo

) {
}
