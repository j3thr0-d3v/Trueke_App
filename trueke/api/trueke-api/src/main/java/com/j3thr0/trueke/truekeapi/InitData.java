package com.j3thr0.trueke.truekeapi;

import com.j3thr0.trueke.truekeapi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserRepository userRepository;
    @PostConstruct
    void init(){

//        System.out.println("\n\n\n\n\n\n\n\n");
//        System.out.println("------------------------------------------------------------------------");
//
//
//        System.out.println("------------------------------------------------------------------------");
//        System.out.println("\n\n\n\n\n\n\n\n");

    }
}
