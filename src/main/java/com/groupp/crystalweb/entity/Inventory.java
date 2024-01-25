package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory extends SerializableObject{
    @NotBlank(message = "Item name is required")
    private String name;

    @NotNull(message = "Available Quantity is required")
    private Integer avaQuantity;

    private Integer rop;

    @NotNull(message = "Expire Date is required")
    @JsonFormat(pattern = DateFormats.LOCAL_DATE)
    private LocalDate expDate;

    @NotNull(message = "Supplier Information is required")
    @ManyToOne
    private Supplier supInfo;
}