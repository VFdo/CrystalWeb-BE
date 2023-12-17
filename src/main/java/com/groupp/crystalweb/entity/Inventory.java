package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory extends SerializableObject{

    @NotBlank(message = "Item name is required")
    private String name;

    @NotBlank(message = "Available Quantity is required")
    private Integer avaQuantity;

    private Integer rop;

    @NotBlank(message = "Expire Date is required")
    private Date expDate;

    @NotBlank(message = "Supplier Information is required")
    private String supInfo;

//    public String getRefId() {
//        return refId;
//    }
}