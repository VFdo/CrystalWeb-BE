package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Gender;

import java.time.LocalDate;

public record PetRequest(
        String name,
        LocalDate dob,
        String typeOfAnimal,
        Gender gender,
        byte[] photo,
        String clientRefId
) {
}
