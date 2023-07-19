package com.example.demomaplerad.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String image;
    private String number;
    private String country;
    @OneToOne(mappedBy = "identity")
    private User customer;
}
