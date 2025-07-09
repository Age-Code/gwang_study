package org.example.myblog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.example.myblog.dto.RefreshTokenDto;

@Getter
@Setter
@EntityListeners(EntityListeners.class)
@Entity
public class RefreshToken extends AuditingFields{

    Long userId;

    @Column(unique = true)
    String token;

    protected RefreshToken() {}
    private RefreshToken(Boolean deleted, Long userId, String token) {
        this.deleted = deleted;
        this.userId = userId;
        this.token = token;
    }
    public static RefreshToken of(String token, Long userId) {
        return new RefreshToken(false, userId, token);
    }

    public RefreshTokenDto.CreateResDto toCreateResDto() { return RefreshTokenDto.CreateResDto.builder().id(getId()).build(); }
}