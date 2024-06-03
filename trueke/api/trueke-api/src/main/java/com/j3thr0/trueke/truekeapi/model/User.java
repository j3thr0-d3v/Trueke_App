package com.j3thr0.trueke.truekeapi.model;

import com.j3thr0.trueke.truekeapi.model.enums.UserRoles;
import com.j3thr0.trueke.truekeapi.utils.SetRolesConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
        name = "USERS"
)
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class User implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    @NaturalId
    private String email;
    private String avatarImg;
    @NaturalId
    private String username;
    private String password;
    @Builder.Default
    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();
    @CreatedDate
    private LocalDateTime createdAt;
    @Convert(converter = SetRolesConverter.class)
    private Set<UserRoles> roles;
    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role-> "ROLE_"+role)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
