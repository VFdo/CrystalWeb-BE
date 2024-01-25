package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
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
public class User {
    @Id
    private String userId;
    private String userName;
    private String password;
    private Role role;



}
