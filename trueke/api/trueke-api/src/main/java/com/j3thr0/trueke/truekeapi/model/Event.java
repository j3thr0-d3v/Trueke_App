package com.j3thr0.trueke.truekeapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "EVENTS"
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String headline;
    private String description;
    private String location;
    private String eventImg;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(
            name = "ASSOCIATION_ID",
            foreignKey = @ForeignKey(name = "FK_EVENTS_TO_ASSOCIATIONS")
    )
    private Association organizer;
    @ManyToMany
    @JoinTable(
            name = "COLLABORATIONS",
            joinColumns = @JoinColumn(
                    name = "collaborator_id",
                    foreignKey = @ForeignKey(name = "FK_COLLABORATIONS_TO_EVENTS"),
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id",
                    foreignKey = @ForeignKey(name = "FK_COLLABORATIONS_TO_COLLABORATORS"),
                    nullable = false
            )
    )
    @Builder.Default
    private List<Collaborator> collaborators = new ArrayList<>();

}
