package com.j3thr0.trueke.truekeapi.controller;

import com.j3thr0.trueke.truekeapi.dto.EventDTO.*;
import com.j3thr0.trueke.truekeapi.model.Event;
import com.j3thr0.trueke.truekeapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    //GET ALL EVENTS [DONE]
    @GetMapping("/")
    public ResponseEntity<List<EventResponse>> getAllEvents(){
        List<Event> events = eventService.getAll();
        return ResponseEntity.ok(
                events.stream().map(EventResponse::of).toList()
        );
    }

    //GET AN EVENT [DONE]
    @GetMapping("/{event_id}")
    public ResponseEntity<EventResponse> getAnEvent(
            @PathVariable UUID event_id
    ){
        Event event = eventService.findById(event_id);
        return ResponseEntity.ok(EventResponse.of(event));
    }

}
