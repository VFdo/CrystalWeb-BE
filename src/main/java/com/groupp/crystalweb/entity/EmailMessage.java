package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class EmailMessage {
    private String to;
    private String subject;
    private String message;
}
