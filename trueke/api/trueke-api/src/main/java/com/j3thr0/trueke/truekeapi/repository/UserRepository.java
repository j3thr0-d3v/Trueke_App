package com.j3thr0.trueke.truekeapi.repository;

import com.j3thr0.trueke.truekeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByUsername(String username);
    Optional<User> findFirstByEmail(String email);
}
