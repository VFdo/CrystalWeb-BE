package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="user")

//@allArgsConstructor
public class User extends SerializableObject {
    @NotBlank(message = "User Name is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private String email;

    private String dataId;

    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private List<Role> role;
}
