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
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String phone_country_code;
    private String phone_number;
    @OneToOne(mappedBy = "phone")
    private Customer customer;
}
