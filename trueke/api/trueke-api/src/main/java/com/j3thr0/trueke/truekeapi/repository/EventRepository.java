package com.j3thr0.trueke.truekeapi.repository;

import com.j3thr0.trueke.truekeapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
