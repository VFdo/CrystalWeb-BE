package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email")

public class EmailMessage extends SerializableObject {
    private String reciever;
    private String subject;
    private String message;
}
