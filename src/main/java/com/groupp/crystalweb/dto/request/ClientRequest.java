package com.groupp.crystalweb.dto.request;


public record ClientRequest(
        String refId,
        String name,
        String nic,
        String address,
        String phone,
        String email,
        String role
) {
}
