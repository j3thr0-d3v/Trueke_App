package com.j3thr0.trueke.truekeapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "COLLABORATORS"
)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class Collaborator extends User{

    private int age;
    private String skills;
    private String motivation;
    @ManyToMany(mappedBy = "collaborators")
    @Builder.Default
    private List<Event> collaborations = new ArrayList<>();

    //HELPERS
    public void singUpAnEvent(Event event){
        collaborations.add(event);
        event.getCollaborators().add(this);
    }

    public void unregisterFromAnEvent(Event event){
        collaborations.remove(event);
        event.getCollaborators().remove(this);
    }
}
