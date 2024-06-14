package com.j3thr0.trueke.truekeapi.service;

import com.j3thr0.trueke.truekeapi.dto.EventDTO.*;
import com.j3thr0.trueke.truekeapi.model.Association;
import com.j3thr0.trueke.truekeapi.model.Event;
import com.j3thr0.trueke.truekeapi.repository.EventRepository;
import com.j3thr0.trueke.truekeapi.settings.files.service.StorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final StorageService storageService;

    public Event createEvent(CreateEventRequest eventRequest, Association organizer, MultipartFile file){
        Event event = Event.builder()
                .title(eventRequest.title())
                .description(eventRequest.description())
                .location(eventRequest.location())
                .eventImg(storageService.store(file))
                .startDate(eventRequest.startDate())
                .endDate(eventRequest.endDate())
                .organizer(organizer)
                .build();

        return eventRepository.save(event);
    }

    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    public void deleteEventById(UUID id){
        eventRepository.deleteById(id);
    }

    public Event updateEvent(UpdateEventRequest updateEventRequest, Event event){
        event.setTitle(updateEventRequest.title());
        event.setDescription(updateEventRequest.description());
        event.setLocation(updateEventRequest.location());
        event.setStartDate(updateEventRequest.startDate());
        event.setEndDate(updateEventRequest.endDate());
        return eventRepository.save(event);
    }

    public Event findById(UUID id){
        return eventRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Event not found"));
    }
}
