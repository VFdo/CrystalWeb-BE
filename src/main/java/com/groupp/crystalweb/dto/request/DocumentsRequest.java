package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Documents;

public record DocumentsRequest(
        byte[] doc,
        String name,
        String petRefId,
        Documents.Status docStatus
) {
}
