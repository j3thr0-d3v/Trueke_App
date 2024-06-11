package com.j3thr0.trueke.truekeapi.controller;

import com.j3thr0.trueke.truekeapi.dto.AssociationDTO.*;
import com.j3thr0.trueke.truekeapi.dto.AuthDTO.*;
import com.j3thr0.trueke.truekeapi.dto.EventDTO.*;
import com.j3thr0.trueke.truekeapi.model.Association;
import com.j3thr0.trueke.truekeapi.model.Event;
import com.j3thr0.trueke.truekeapi.model.User;
import com.j3thr0.trueke.truekeapi.model.Worker;
import com.j3thr0.trueke.truekeapi.service.AssociationService;
import com.j3thr0.trueke.truekeapi.service.EventService;
import com.j3thr0.trueke.truekeapi.service.WorkerService;
import com.j3thr0.trueke.truekeapi.settings.security.jwt.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/association")
@RequiredArgsConstructor
public class AssociationController {

    private final RefreshTokenService refreshTokenService;
    private final EventService eventService;
    private final AssociationService associationService;
    private final WorkerService workerService;

    //GET ALL ASSOCIATIONS [DONE] [DEP]
    @GetMapping("/")
    public ResponseEntity<List<AssociationResponse>> getAllAssociations(){
        List<Association> associations = associationService.findAll();
        return ResponseEntity.ok(associations.stream().map(AssociationResponse::of).toList());
    }

    //GET AN ASSOCIATION [DONE]
    @GetMapping("/{association_id}")
    public ResponseEntity<AssociationResponse> GetAnAssociation(
            @PathVariable UUID association_id
    ){
        Association association = associationService.findById(association_id);
        return ResponseEntity.ok(AssociationResponse.of(association));
    }

    //EDIT AN ASSOCIATION [DONE]
    @PreAuthorize("#association_id == authentication.principal.getAssociation().getId()")
    @PutMapping("/{association_id}")
    public ResponseEntity<AssociationResponse> editAnAssociation(
            @RequestBody UpdateAssociationRequest updateAssociationRequest,
            @PathVariable UUID association_id
    ){
        Association association = associationService.findById(association_id);
        Association modified = associationService.updateAnAssociation(association, updateAssociationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(AssociationResponse.of(modified));
    }

    //DELETE AN ASSOCIATION [DONE]
    @PreAuthorize("#association_id == authentication.principal.getAssociation().getId()")
    @DeleteMapping("/{association_id}")
    public ResponseEntity<?> deleteAnAssociation(
            @PathVariable UUID association_id,
            @AuthenticationPrincipal User user
    ){
        refreshTokenService.deleteByUser(user);
        associationService.deleteAnAssociation(association_id);
        return ResponseEntity.noContent().build();
    }

    //GET ALL EVENTS FROM AN ASSOCIATION [DONE]
    @GetMapping("/{association_id}/event")
    public ResponseEntity<List<EventResponse>> getAllEventsFromAnAssociation(
            @PathVariable UUID association_id
    ){
        List<Event> events = associationService.findById(association_id).getEvents();
        return ResponseEntity.status(HttpStatus.OK).body(
                events.stream().map(EventResponse::of).toList()
        );
    }

    //CREATE EVENT [DONE]
    @PreAuthorize("#association_id == authentication.principal.getAssociation().getId()")
    @PostMapping("/{association_id}/event")
    public ResponseEntity<EventResponse> createEvent (
            @RequestBody CreateEventRequest createEventRequest,
            @PathVariable UUID association_id
    ){
        Association organizer = associationService.findById(association_id);
        Event event = eventService.createEvent(createEventRequest, organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(EventResponse.of(event));
    }

    //EDIT AN EVENT [DONE]
    @PreAuthorize("#association_id == authentication.principal.getAssociation().getId()")
    @PutMapping("/{association_id}/event/{event_id}")
    public ResponseEntity<EventResponse> updateAnEvent(
            @PathVariable UUID association_id,
            @PathVariable UUID event_id,
            @RequestBody UpdateEventRequest updateEventRequest
    ){
        Event event = eventService.findById(event_id);
        Event modified = eventService.updateEvent(updateEventRequest, event);
        return ResponseEntity.status(HttpStatus.CREATED).body(EventResponse.of(modified));
    }

    //DELETE AN EVENT []
    @PreAuthorize("#association_id == authentication.principal.getAssociation().getId()")
    @DeleteMapping("/{association_id}/event/{event_id}")
    public ResponseEntity<?> deleteAnEventFromAnAssociation(
            @PathVariable UUID association_id,
            @PathVariable UUID event_id
    ){
        eventService.deleteEventById(event_id);
        return ResponseEntity.noContent().build();
    }

    //GET ALL WORKERS FROM AN ASSOCIATION [DONE]
    @GetMapping("/{association_id}/workers")
    public ResponseEntity<List<UserResponse>> getAllWorkers(
            @PathVariable UUID association_id
    ){
        List<Worker> staff = associationService.findById(association_id).getStaff();
        return ResponseEntity.ok(
                staff.stream().map(UserResponse::fromWorker).toList()
        );
    }

    //DELETE A WORKER FROM AN ASSOCIATION []
    @PreAuthorize("#association_id == authentication.principal.getAssociation().getId()")
    @DeleteMapping("/{association_id}/workers/{worker_id}")
    public ResponseEntity<?> deleteAWorkerFromAnAssociation(
            @PathVariable UUID association_id,
            @PathVariable UUID worker_id
    ){
        Worker worker = workerService.findById(worker_id);
        associationService.assertWorkerBelongToAssociation(association_id, worker);
        workerService.deleteWorker(worker_id);
        return ResponseEntity.noContent().build();
    }














}
