package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class SerializableObject implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    public String refId;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormats.LOCAL_DATE_TIME)
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormats.LOCAL_DATE_TIME)
    private LocalDateTime lastModifiedDate;

//    TODO: implement following with security
//    @CreatedBy
//    @Column(name = "created_by", updatable = false)
//    private String createdBy;
//
//    @LastModifiedBy
//    @Column(name = "last_modified_by")
//    private String lastModifiedBy;
}



