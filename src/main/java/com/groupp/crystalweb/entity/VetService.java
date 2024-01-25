package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vetService")
public class VetService {
    @Id
    private String refId;
    private String name;
    private Float avgTime;
    private Float amount;
}
