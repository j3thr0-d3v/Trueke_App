package com.j3thr0.trueke.truekeapi.settings.security.jwt.refresh;

import com.j3thr0.trueke.truekeapi.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RefreshToken {

    @Id
    private UUID id;

    @MapsId
    @OneToOne()
    @JoinColumn(
            name = "user_id",
            columnDefinition = "uuid",
            foreignKey = @ForeignKey(name = "FK_REFRESH_TOKEN_TO_USERS")
    )
    private User user;

    @NaturalId
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @CreatedDate
    private Instant createdAt;
}
