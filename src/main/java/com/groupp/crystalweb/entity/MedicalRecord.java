package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "medical_record")
public class MedicalRecord extends SerializableObject{
    @JsonFormat(pattern = DateFormats.LOCAL_DATE)
    private LocalDate date;

    @ManyToOne
    @JsonIgnore
    private Pet petRefId;

    @JsonProperty("PetId")
    public String getPetId() {
        return (petRefId != null) ? petRefId.getRefId() : null;
    }

    @ManyToOne
    @JsonIgnore
    private Employee vetRefId;

    @JsonProperty("VetId")
    public String getVetId() {
        return (vetRefId != null) ? vetRefId.getRefId() : null;
    }

    @OneToMany
    private List<Documents> docs;

    @JsonFormat(pattern = DateFormats.LOCAL_DATE)
    private LocalDate reminderDate;
    private String treatment;
    private ReminderType reminderType;
}
