package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pet")
public class Pet extends SerializableObject{
    @NotBlank(message = "Name is required!")
    private String name;
    @JsonFormat(pattern= DateFormats.LOCAL_DATE)
    private LocalDate dob;
//    TODO: not urgent : convert to ENUM
    @NotBlank(message = "Animal Type is required!")
    private String typeOfAnimal;
    private Gender gender;
    private byte[] photo;
    @ManyToOne
    @JsonIgnore
    private Client client;

    @JsonProperty("clientId")
    public String getClientId() {
        return (client != null) ? client.getRefId() : null;
    }
}
