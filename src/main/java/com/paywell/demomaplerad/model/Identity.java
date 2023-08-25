package com.paywell.demomaplerad.model;

import com.paywell.demomaplerad.model.enums.IdentityType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "identity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private IdentityType identityType;
    private String imageUrl;
    private String docNumber;
    private String country;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
