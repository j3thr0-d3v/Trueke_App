package com.j3thr0.trueke.truekeapi.controller;

import com.j3thr0.trueke.truekeapi.dto.AuthDTO.*;
import com.j3thr0.trueke.truekeapi.dto.CollaboratorDTO.*;
import com.j3thr0.trueke.truekeapi.dto.EventDTO.*;
import com.j3thr0.trueke.truekeapi.model.Collaborator;
import com.j3thr0.trueke.truekeapi.model.Event;
import com.j3thr0.trueke.truekeapi.service.CollaboratorService;
import com.j3thr0.trueke.truekeapi.service.EventService;
import com.j3thr0.trueke.truekeapi.settings.security.jwt.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collaborator")
public class CollaboratorController {

    private final CollaboratorService collaboratorService;
    private final EventService eventService;
    private final RefreshTokenService refreshTokenService;

    //GET COLLABORATOR DATA [DONE]
    @PreAuthorize("#collaborator_id == authentication.principal.getId()")
    @GetMapping("/{collaborator_id}")
    public ResponseEntity<UserResponse> getCollaboratorData(
            @PathVariable UUID collaborator_id
    ) {
        Collaborator collaborator = collaboratorService.findById(collaborator_id);
        return ResponseEntity.ok(UserResponse.fromCollaborator(collaborator));
    }

    //EDIT PROFILE [DONE]
    @PreAuthorize("#collaborator_id == authentication.principal.getId()")
    @PutMapping("/{collaborator_id}")
    public ResponseEntity<UserResponse> updateCollaborator(
            @PathVariable UUID collaborator_id,
            @RequestPart("body") UpdateCollaboratorRequest updateCollaboratorRequest,
            @RequestPart("file") MultipartFile file
            ) {
        Collaborator collaborator = collaboratorService.findById(collaborator_id);
        Collaborator modified = collaboratorService.editCollaborator(collaborator, updateCollaboratorRequest, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromCollaborator(modified));

    }

    //SING UP FOR AN EVENT [DONE]
    @PreAuthorize("#collaborator_id == authentication.principal.getId()")
    @PostMapping("/{collaborator_id}/event/{event_id}")
    public ResponseEntity<SingUpEventResponse> singUpAnEvent(
            @PathVariable UUID collaborator_id,
            @PathVariable UUID event_id
    ){
        Collaborator collaborator= collaboratorService.findById(collaborator_id);
        Event event = eventService.findById(event_id);
        Collaborator subscribed = collaboratorService.singUpAnEvent(collaborator, event);
        return ResponseEntity.status(HttpStatus.CREATED).body(SingUpEventResponse.of(subscribed,event));
    }

    //UNREGISTER FROM AN EVENT [DONE]
    @PreAuthorize("#collaborator_id == authentication.principal.getId()")
    @DeleteMapping("/{collaborator_id}/event/{event_id}")
    public ResponseEntity<?> unregisterFromAnEvent(
            @PathVariable UUID collaborator_id,
            @PathVariable UUID event_id
    ){
        Collaborator collaborator= collaboratorService.findById(collaborator_id);
        Event event = eventService.findById(event_id);
        collaboratorService.unregisterFromAnEvent(collaborator, event);
        return ResponseEntity.noContent().build();
    }

    //GET ALL EVENTS SUBSCRIBED BY A COLLABORATOR [DONE]
    @PreAuthorize("#collaborator_id == authentication.principal.getId()")
    @GetMapping("/{collaborator_id}/event")
    public ResponseEntity<List<EventResponse>> getEventsSubscribedByACollaborator(
            @PathVariable UUID collaborator_id
    ){
        List<Event> eventsSubscribed = collaboratorService.findById(collaborator_id).getCollaborations();
        return ResponseEntity.ok(
                eventsSubscribed.stream().map(EventResponse::of).toList()
        );
    }

    //DELETE ACCOUNT [DONE]
    @PreAuthorize("#collaborator_id == authentication.principal.getId()")
    @DeleteMapping("/{collaborator_id}")
    public ResponseEntity<?> deleteCollaboratorAccount(
            @PathVariable UUID collaborator_id
    ){
        Collaborator collaborator = collaboratorService.findById(collaborator_id);
        refreshTokenService.deleteByUser(collaborator);
        collaboratorService.deleteCollaboratorAccount(collaborator_id);
        return ResponseEntity.noContent().build();
    }

}
