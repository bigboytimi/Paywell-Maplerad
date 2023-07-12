package com.example.demomaplerad.model;

import com.example.demomaplerad.enums.Country;
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
    private String id;
    private String type;
    private String image;
    private String number;
    private Country country;
    @OneToOne(mappedBy = "identity")
    private Customer customer;
}
