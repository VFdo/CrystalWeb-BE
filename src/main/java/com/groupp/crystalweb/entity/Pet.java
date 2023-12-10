package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dob;
//    TODO: not urgent : convert to ENUM
    private String typeOfAnimal;
    private byte[] photo;
    @ManyToOne
    private Client client;
}
