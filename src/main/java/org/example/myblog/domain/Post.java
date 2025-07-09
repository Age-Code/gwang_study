package org.example.myblog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.example.myblog.dto.PostDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post extends AuditingFields{

    Long userId;
    String title;
    String content;

    protected Post() {}
    private Post(Boolean deleted, Long userId, String title, String content) {
        this.deleted = deleted;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    public static Post of(Long userId, String title, String content) {
        return new Post(false, userId, title, content);
    }

    public PostDto.CreateResDto toCreateRespDto() {
        return PostDto.CreateResDto.builder().id(getId()).build();
    }
}
