package com.j3thr0.trueke.truekeapi.dto;

public interface WorkerDTO {

    public record UpdateWorkerRequest (
            String name,
            String surname,
            String phoneNumber,
            String career
    ){}
}
