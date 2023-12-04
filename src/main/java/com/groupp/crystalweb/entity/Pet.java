package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
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
    private String name;
    private LocalDate dob;
//    TODO: not urgent : convert to ENUM
    private String typeOfAnimal;
    private byte[] photo;
    @ManyToOne
    private Client client;
}
