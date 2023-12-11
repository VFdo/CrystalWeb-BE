package com.groupp.crystalweb.dto.request;

import java.time.LocalDate;

public record PetRequest(
        String refId,
        String name,
        LocalDate dob,
        String typeOfAnimal,
        byte[] photo,
        String clientRefId
) {
}
