package com.example.demomaplerad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Card {
    @Id
    private String id;

}
