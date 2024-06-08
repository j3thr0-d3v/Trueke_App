package com.j3thr0.trueke.truekeapi;

import com.j3thr0.trueke.truekeapi.model.Event;
import com.j3thr0.trueke.truekeapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

    @PostConstruct
    void init(){
        Event event = Event.builder().build();
    }
}
