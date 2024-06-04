package com.j3thr0.trueke.truekeapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;

import java.util.UUID;

@Entity
@Table(
        name = "WORKERS"
)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Worker extends User {

    @Column(name = "dni", unique = true)
    private String dni;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    private String career;
    @ManyToOne
    @JoinColumn(
            name = "ASSOCIATION_ID",
            foreignKey = @ForeignKey(name = "FK_WORKERS_TO_ASSOCIATIONS")
    )
    private Association association;
}
