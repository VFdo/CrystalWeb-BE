package com.groupp.crystalweb.dto.request;

public record EmailRequest(
        String reciever,
        String subject,
        String body,
        String file
) {
}
