package com.j3thr0.trueke.truekeapi.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "ASSOCIATIONS"
)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Association {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String address;
    private String mission;
    private String logoImg;
    private String bannerImg;
    @NaturalId
    private String cif;
    @OneToMany(
            mappedBy = "association"
//            cascade = CascadeType.ALL
    )
    @Builder.Default
    private List<Worker> staff = new ArrayList<>();
    @OneToMany(
            mappedBy = "organizer",
            cascade = CascadeType.REMOVE
    )
    @Builder.Default
    private List<Event> events = new ArrayList<>();


}
