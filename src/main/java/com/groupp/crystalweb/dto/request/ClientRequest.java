package com.groupp.crystalweb.dto.request;


import java.util.List;

public record ClientRequest(
        String refId,
        String name,
        String nic,
        String address,
        List<String> phone,
        String email,
        String role
) {
}
