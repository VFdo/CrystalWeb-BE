package com.groupp.crystalweb.dto.request;

import java.util.Date;

public record PetRequest(
        String refId,
        String name,
        Date dob,
        String typeOfAnimal,
        byte[] photo
) {
}
