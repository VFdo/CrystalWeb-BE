package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
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
    private String name;
    private String nic;
    private String address;
    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "client_id"))
    private List<String> phone;
    private String email;
    private String role;
}


// ------ CHANGES: ------
//- phone changed to List
