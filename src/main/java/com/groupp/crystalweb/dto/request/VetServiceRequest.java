package com.groupp.crystalweb.dto.request;

import java.util.Date;

public record VetServiceRequest(
        String refId,
        String name,
        Float avgTime,
        Float amount
) {
}
