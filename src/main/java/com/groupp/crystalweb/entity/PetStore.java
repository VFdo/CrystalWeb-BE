package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "petstore")
public class PetStore {
    @Id
    private String refId;
    private String name;
    private String description;
    private float unitprice;
    private Integer productid;
    private byte[] photo;
    private Status catergory;

}
