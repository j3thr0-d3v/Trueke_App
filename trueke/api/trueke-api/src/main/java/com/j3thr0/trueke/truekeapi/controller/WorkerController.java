package com.j3thr0.trueke.truekeapi.controller;

import com.j3thr0.trueke.truekeapi.dto.AuthDTO.*;
import com.j3thr0.trueke.truekeapi.dto.WorkerDTO.*;
import com.j3thr0.trueke.truekeapi.model.Worker;
import com.j3thr0.trueke.truekeapi.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/worker")
public class WorkerController {

    private final WorkerService workerService;

    //GET A WORKER [DONE]
    @GetMapping("/{worker_id}")
    public ResponseEntity<UserResponse> getAWorker(
            @PathVariable UUID worker_id
            ){
        Worker worker = workerService.findById(worker_id);
        return ResponseEntity.ok(UserResponse.fromWorker(worker));
    }

    //EDIT PROFILE [DONE]
    @PreAuthorize("#worker_id == authentication.principal.getId()")
    @PutMapping("/{worker_id}")
    public ResponseEntity<UserResponse> updateWorkerInfo(
            @PathVariable UUID worker_id,
            @RequestBody UpdateWorkerRequest updateWorkerRequest
    ){
        Worker worker = workerService.findById(worker_id);
        Worker modified = workerService.editWorker(updateWorkerRequest, worker);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromWorker(modified));
    }



}
