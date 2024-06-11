package com.j3thr0.trueke.truekeapi.service;

import com.j3thr0.trueke.truekeapi.dto.CollaboratorDTO.*;
import com.j3thr0.trueke.truekeapi.model.Collaborator;
import com.j3thr0.trueke.truekeapi.model.Event;
import com.j3thr0.trueke.truekeapi.repository.CollaboratorRepository;
import com.j3thr0.trueke.truekeapi.settings.files.service.StorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;
    private final StorageService storageService;

    public Collaborator findById(UUID id){
        return collaboratorRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Collaborator not found"));
    }

    public Collaborator editCollaborator(Collaborator collaborator, UpdateCollaboratorRequest updateRequest, MultipartFile file){

        storageService.deleteFile(collaborator.getAvatarImg());

        collaborator.setAvatarImg(storageService.store(file));
        collaborator.setName(updateRequest.name());
        collaborator.setSurname(updateRequest.surname());
        collaborator.setSkills(updateRequest.skills());
        collaborator.setMotivation(updateRequest.motivation());
        return collaboratorRepository.save(collaborator);
    }

    public Collaborator singUpAnEvent(Collaborator collaborator, Event event){
        if(event.getCollaborators().contains(collaborator))
            throw new RuntimeException("Collaborator already registered at this event");
        collaborator.singUpAnEvent(event);
        return collaboratorRepository.save(collaborator);
    }

    public void unregisterFromAnEvent(Collaborator collaborator, Event event){
        if(event.getCollaborators().contains(collaborator)) {
            collaborator.unregisterFromAnEvent(event);
            collaboratorRepository.save(collaborator);
        }else
            throw new EntityNotFoundException("Collaborator not registered on this event");
    }

    public void deleteCollaboratorAccount(UUID id){
        collaboratorRepository.deleteById(id);
    }
}
