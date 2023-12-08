package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client extends SerializableObject {
    @NotBlank(message = "Client name is required")
    private String name;

    @NotBlank(message = "Client NIC number is required")
    private String nic;

    private String address;

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "client_id"))
    private List<String> phone;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Client email is required")
    private String email;

    private Role role = Role.CLIENT;
}


// ------ CHANGES: ------
//- phone changed to List
//- Role datatype changed
