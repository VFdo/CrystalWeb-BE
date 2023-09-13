package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Demo {
    @Id
    private String refId;
    private String demoString;
    private int demoInt;
    private Boolean demoBool;
//    private List<String> demoList;
}
