package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance extends SerializableObject {

    @ManyToOne
    @JsonIgnore
    private Employee employee;

    @NotNull(message = "Check in time is Required")
    @JsonFormat(pattern = DateFormats.LOCAL_DATE_TIME)
    private LocalDateTime inTime;

    @NotNull(message = "Check out time is Required")
    @JsonFormat(pattern = DateFormats.LOCAL_DATE_TIME)
    private LocalDateTime outTime;

    private int overTimeHours;

    private String notes;


}
