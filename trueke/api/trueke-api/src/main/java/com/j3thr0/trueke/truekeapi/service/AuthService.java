package com.j3thr0.trueke.truekeapi.service;

import com.j3thr0.trueke.truekeapi.dto.AuthDTO.*;
import com.j3thr0.trueke.truekeapi.model.Association;
import com.j3thr0.trueke.truekeapi.model.Collaborator;
import com.j3thr0.trueke.truekeapi.model.User;
import com.j3thr0.trueke.truekeapi.model.Worker;
import com.j3thr0.trueke.truekeapi.model.enums.UserRoles;
import com.j3thr0.trueke.truekeapi.repository.AssociationRepository;
import com.j3thr0.trueke.truekeapi.repository.UserRepository;
import com.j3thr0.trueke.truekeapi.settings.files.service.StorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AssociationRepository associationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StorageService storageService;

    public Association registerNewAssociation(RegisterAssociationRequest newAssociationRequest){
        Association association = Association.builder()
                .name(newAssociationRequest.name())
                .address(newAssociationRequest.address())
                .mission(newAssociationRequest.mission())
                .cif(newAssociationRequest.cif())
                .build();
        association =  associationRepository.save(association);
        Worker worker = registerNewWorker(newAssociationRequest.founder());
        worker.setAssociation(association);
        worker.setRoles(EnumSet.of(UserRoles.WORKER,UserRoles.STAFF));
        userRepository.save(worker);

        return association;
    }

    public Collaborator registerNewCollaborator(SingUpCollaboratorRequest newCollaboratorRequest, MultipartFile file){
        Collaborator collaborator = Collaborator.builder()
                .avatarImg(storageService.store(file))
                .name(newCollaboratorRequest.name())
                .surname(newCollaboratorRequest.surname())
                .email(newCollaboratorRequest.email())
                .username(newCollaboratorRequest.username())
                .password(passwordEncoder.encode(newCollaboratorRequest.password()))
                .roles(EnumSet.of(UserRoles.COLLABORATOR))
                .age(newCollaboratorRequest.age())
                .skills(newCollaboratorRequest.skills())
                .motivation(newCollaboratorRequest.motivation())
                .build();

        return userRepository.save(collaborator);
    }

    public Worker registerNewWorker(SingUpWorkerRequest newWorkerRequest){
        Worker worker = Worker.builder()
                .name(newWorkerRequest.name())
                .surname(newWorkerRequest.surname())
                .email(newWorkerRequest.email())
                .username(newWorkerRequest.username())
                .password(passwordEncoder.encode(newWorkerRequest.password()))
                .roles(EnumSet.of(UserRoles.WORKER))
                .phoneNumber(newWorkerRequest.phoneNumber())
                .dni(newWorkerRequest.dni())
                .career(newWorkerRequest.career())
                .association(newWorkerRequest.associationId()!=null?
                        associationRepository.findById(newWorkerRequest.associationId())
                                .orElseThrow(() -> new EntityNotFoundException("Association not found"))
                        : null
                        )
                .build();

        return userRepository.save(worker);
    }

    public User editPassword(UUID userId, String newPassword){
        return userRepository.findById(userId)
                .map(u ->{
                    u.setPassword(passwordEncoder.encode(newPassword));
                    return userRepository.save(u);
                }).orElseThrow(()->new EntityNotFoundException("User not found"));
    }

    public void passwordMatch(User user, String clearPassword){
        if(!passwordEncoder.matches(clearPassword, user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password data error");
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findFirstByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findFirstByEmail(email);
    }

    public Optional<User> findById(UUID id){
        return userRepository.findById(id);
    }

}
