package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier extends SerializableObject {
    private String name; //company/ dealer

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "supplier_phone_numbers", joinColumns = @JoinColumn(name = "client_id"))
    private List<String> phone;

    @Email(message = "Invalid email format")
    private String email;

    private String notes;
}
