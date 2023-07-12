package com.example.demomaplerad.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass

public abstract class BaseEntity implements Serializable {
    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedDate;
}
