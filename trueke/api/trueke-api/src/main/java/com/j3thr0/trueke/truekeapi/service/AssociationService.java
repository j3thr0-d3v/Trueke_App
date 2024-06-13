package com.j3thr0.trueke.truekeapi.service;

import com.j3thr0.trueke.truekeapi.dto.AssociationDTO.*;
import com.j3thr0.trueke.truekeapi.model.Association;
import com.j3thr0.trueke.truekeapi.model.Worker;
import com.j3thr0.trueke.truekeapi.repository.AssociationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssociationService {

    private final AssociationRepository associationRepository;

    public Association updateAnAssociation(
            Association association,
            UpdateAssociationRequest updateAssociationRequest
    ){
        association.setName(updateAssociationRequest.name());
        association.setMission(updateAssociationRequest.mission());
        association.setAddress(updateAssociationRequest.address());
        return associationRepository.save(association);
    }

    public void assertWorkerBelongToAssociation(UUID associationId, Worker worker){
        Association association = findById(associationId);
        if(!association.getStaff().contains(worker))
            throw new EntityNotFoundException("Worker does not belong to this association");
    }

    public void deleteAnAssociation(UUID id){
        associationRepository.deleteById(id);
    }

    public Association findById(UUID id){
        return associationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Association not found"));
    }

    public Association findByName(String name){
        return associationRepository.findFirstByName(name)
                .orElseThrow(()-> new EntityNotFoundException("Associaiton not found"));
    }

    public List<Association> findAll(){
        return associationRepository.findAll();
    }
}
