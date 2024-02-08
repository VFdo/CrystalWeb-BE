package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="user")

//@allArgsConstructor
public class User extends SerializableObject {
    @NotBlank(message = "User Name is required")
    private String userName;

    @NotBlank(message = "Password is required")
    private String password;

    private Role role;



}
