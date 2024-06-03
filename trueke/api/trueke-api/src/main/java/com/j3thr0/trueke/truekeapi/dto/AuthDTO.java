package com.j3thr0.trueke.truekeapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.j3thr0.trueke.truekeapi.model.Association;
import com.j3thr0.trueke.truekeapi.model.Collaborator;

import com.j3thr0.trueke.truekeapi.model.User;
import com.j3thr0.trueke.truekeapi.model.Worker;
import com.j3thr0.trueke.truekeapi.model.enums.UserRoles;
import lombok.Builder;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public interface AuthDTO {

    public record RegisterAssociationRequest(
            String name,
            String address,
            String mission,
            String cif,

            SingUpWorkerRequest founder
    ) {
    }

    public record SingUpCollaboratorRequest(
            String name,
            String surname,
            String email,
            String username,
            int age,
            String skills,
            String motivation,
            String password,
            String verifyPassword
    ) {
    }

    public record SingUpWorkerRequest(
            String name,
            String surname,
            String email,
            String username,
            String phoneNumber,
            String dni,
            String career,
            UUID associationId,
            String password,
            String verifyPassword
    ) {
    }

    public record LoginRequest(
            String username,
            String password
    ) {
    }

    public record ChangePasswordRequest(
            String oldPassword,
            String newPassword,
            String verifyPassword
    ) {
    }

    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record UserResponse(
            UUID id,
            String username,
            String name,
            String surname,
            String roles,
            String association,
            @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
            LocalDateTime createdAt,
            String token,
            String refreshToken
    ) {

        public static UserResponse fromUser(User user) {
            return user instanceof Worker ?
                UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .roles(getRoles(user.getRoles()))
                    .createdAt(user.getCreatedAt())
                    .association(((Worker) user).getAssociation().getName())
                    .build()
                :UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .name(user.getName())
                    .surname(user.getSurname())
                    .roles(getRoles(user.getRoles()))
                    .createdAt(user.getCreatedAt())
                    .build();
        }

        public static UserResponse fromCollaborator(Collaborator collaborator) {
            return UserResponse.builder()
                    .id(collaborator.getId())
                    .username(collaborator.getUsername())
                    .name(collaborator.getName())
                    .surname(collaborator.getSurname())
                    .roles(getRoles(collaborator.getRoles()))
                    .createdAt(collaborator.getCreatedAt())
                    .build();
        }

        public static UserResponse fromWorker(Worker worker) {
            return UserResponse.builder()
                    .id(worker.getId())
                    .username(worker.getUsername())
                    .name(worker.getName())
                    .surname(worker.getSurname())
                    .roles(getRoles(worker.getRoles()))
                    .createdAt(worker.getCreatedAt())
                    .association(worker.getAssociation().getName())
                    .build();
        }

        public static UserResponse fromLogin(User user, String token, String refreshToken) {
            return user instanceof Worker ?
                    UserResponse.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .roles(getRoles(user.getRoles()))
                            .createdAt(user.getCreatedAt())
                            .association(((Worker) user).getAssociation().getName())
                            .token(token)
                            .refreshToken(refreshToken)
                            .build()
                    :
                    UserResponse.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .name(user.getName())
                            .surname(user.getSurname())
                            .roles(getRoles(user.getRoles()))
                            .createdAt(user.getCreatedAt())
                            .token(token)
                            .refreshToken(refreshToken)
                            .build();
        }

        private static String getRoles(Set<UserRoles> roles) {
            return roles.stream()
                    .map(UserRoles::name)
                    .collect(Collectors.joining(","));
        }
    }


}
