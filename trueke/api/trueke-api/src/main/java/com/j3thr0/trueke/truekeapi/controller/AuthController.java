package com.j3thr0.trueke.truekeapi.controller;

import com.j3thr0.trueke.truekeapi.dto.AssociationDTO.*;
import com.j3thr0.trueke.truekeapi.dto.AuthDTO.*;
import com.j3thr0.trueke.truekeapi.model.Association;
import com.j3thr0.trueke.truekeapi.model.Collaborator;
import com.j3thr0.trueke.truekeapi.model.User;
import com.j3thr0.trueke.truekeapi.model.Worker;
import com.j3thr0.trueke.truekeapi.service.AuthService;
import com.j3thr0.trueke.truekeapi.settings.security.jwt.access.JwtProvider;
import com.j3thr0.trueke.truekeapi.settings.security.jwt.refresh.RefreshToken;
import com.j3thr0.trueke.truekeapi.settings.security.jwt.refresh.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    //REGISTER ASSOCIATION [DONE]
    @PostMapping("/register/association")
    public ResponseEntity<AssociationResponse> registerNewAssociation(
            @RequestBody RegisterAssociationRequest registerAssociation
    ){
        Association association = authService.registerNewAssociation(registerAssociation);
        return ResponseEntity.status(HttpStatus.CREATED).body(AssociationResponse.of(association));
    }

    //REGISTER COLLABORATOR [DONE]
    @PostMapping("/register/collaborator")
    public ResponseEntity<UserResponse> registerNewCollaborator (
            @RequestBody SingUpCollaboratorRequest singUpCollaborator
    ){
        Collaborator collaborator = authService.registerNewCollaborator(singUpCollaborator);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromCollaborator(collaborator));
    }

    //REGISTER WORKER [DONE]
    @PostMapping("/register/worker")
    public ResponseEntity<UserResponse> registerNewWorker(
            @RequestBody SingUpWorkerRequest singUpWorker
    ){
        Worker worker = authService.registerNewWorker(singUpWorker);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.fromWorker(worker));
    }


    //LOGIN [DONE]
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login (@RequestBody LoginRequest loginRequest){
        Authentication authentication =
                authManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.username(),
                                loginRequest.password()
                        )
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getName());
        refreshTokenService.deleteByUser(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                UserResponse.fromLogin(user, token, refreshToken.getToken())
        );
    }

    //CHANGE PASSWORD [DONE]
    @PutMapping("/user/changePassword")
    public ResponseEntity<UserResponse> changePassword(
            @RequestBody ChangePasswordRequest changePasswordRequest,
            @AuthenticationPrincipal User userLogged
    ){
        authService.passwordMatch(userLogged, changePasswordRequest.oldPassword());
        User modified =  authService.editPassword(userLogged.getId(), changePasswordRequest.newPassword());
        return ResponseEntity.ok(UserResponse.fromUser(modified));
    }
}