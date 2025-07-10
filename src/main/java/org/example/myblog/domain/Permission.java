package org.example.myblog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.example.myblog.dto.PermissionDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Permission extends AuditingFields{
    String title;
    String content;

    protected Permission() {}
    private Permission(Boolean deleted, String title, String content) {
        this.deleted = deleted;
        this.title = title;
        this.content = content;
    }
    public static Permission of(String title, String content) {
        return new Permission(false, title, content);
    }

    public PermissionDto.CreateResDto toCreateResDto() {
        return PermissionDto.CreateResDto.builder().id(getId()).build();
    }
}
