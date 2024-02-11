package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "documents")
public class Documents extends SerializableObject{
    private byte[] doc;

    @NotBlank(message = "Document name is required!")
    private String name;

    @ManyToOne
    private Pet petRefId;

    public enum Status{
        ACTIVE,
        DELETED,
        OUTDATED
    }

    @NotNull(message = "Document status is required!")
    private Status docStatus;

}
