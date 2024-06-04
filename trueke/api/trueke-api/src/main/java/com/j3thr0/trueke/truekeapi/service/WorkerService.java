package com.j3thr0.trueke.truekeapi.service;

import com.j3thr0.trueke.truekeapi.dto.WorkerDTO.*;
import com.j3thr0.trueke.truekeapi.model.Worker;
import com.j3thr0.trueke.truekeapi.repository.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    public Worker findById(UUID id){
        return workerRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Worker not found"));
    }

    public Worker editWorker(UpdateWorkerRequest updateRequest, Worker worker){
        worker.setName(updateRequest.name());
        worker.setSurname(updateRequest.surname());
        worker.setPhoneNumber(updateRequest.phoneNumber());
        worker.setCareer(updateRequest.career());
        return workerRepository.save(worker);
    }

    public void deleteWorker(UUID id){
        workerRepository.deleteById(id);
    }
}
