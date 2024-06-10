package com.j3thr0.trueke.truekeapi.dto;

import com.j3thr0.trueke.truekeapi.model.Collaborator;
import com.j3thr0.trueke.truekeapi.model.Event;
import lombok.Builder;

public interface CollaboratorDTO {

    public record UpdateCollaboratorRequest(
            String name,
            String surname,
            String skills,
            String motivation
    ){}

    @Builder
    public record SingUpEventResponse(
            String username,
            String statusOfCollaborator,
            EventDTO.EventResponse eventSubscribed
    ){
        public static SingUpEventResponse of (Collaborator collaborator, Event event){
            return SingUpEventResponse.builder()
                    .username(collaborator.getUsername())
                    .statusOfCollaborator("SUBSCRIBED")
                    .eventSubscribed(EventDTO.EventResponse.of(event))
                    .build();
        }
    }
}
