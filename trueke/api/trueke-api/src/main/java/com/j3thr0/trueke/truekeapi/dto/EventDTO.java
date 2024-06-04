package com.j3thr0.trueke.truekeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j3thr0.trueke.truekeapi.model.Association;
import com.j3thr0.trueke.truekeapi.model.Event;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface EventDTO {

    public record CreateEventRequest(
            String title,
            String description,
            String location,
            LocalDateTime startDate,
            LocalDateTime endDate
    ){}

    public record UpdateEventRequest(
            String title,
            String description,
            String location,
            LocalDateTime startDate,
            LocalDateTime endDate
    ){}

    @Builder
    public record EventResponse(
        UUID id,
        String title,
        String description,
        String location,
        @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
        LocalDateTime startDate,
        @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
        LocalDateTime endDate,
        String organizer
    ){
        public static EventResponse of(Event event){
            return EventResponse.builder()
                    .id(event.getId())
                    .title(event.getTitle())
                    .description(event.getDescription())
                    .location(event.getLocation())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .organizer(event.getOrganizer().getName())
                    .build();
        }
    }
}
