package com.example.RentaBikeTestTwo.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    private long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role() {
    }

}