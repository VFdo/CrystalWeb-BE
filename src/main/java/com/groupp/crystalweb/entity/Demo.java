package com.groupp.crystalweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("demo")
public class Demo {
    @Id
    private String refId;
    private String demoString;
    private int demoInt;
    private Boolean demoBool;
    private List<String> demoList;
}
