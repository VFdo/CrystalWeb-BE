package com.groupp.crystalweb.dto.request;

import java.util.List;

public record DemoRequest(
        String refId,
        String demoString,
        int demoInt,
        Boolean demoBool,
        List<String>demoList
) {
}
