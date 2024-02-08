package com.groupp.crystalweb.dto.request;


public record VetServiceRequest(
        String name,
        Boolean chargeByTime,
        String durationMeasure,
        Float amount
) {
}
