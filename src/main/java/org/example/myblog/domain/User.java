package org.example.myblog.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.myblog.dto.UserDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class User extends AuditingFields {
    String username;
    String password;
    String name;

    protected User(){}
    private User(Boolean deleted, String username, String password, String name) {
        this.deleted = deleted;
        this.username = username;
        this.password = password;
        this.name = name;
    }
    public static User of(String username, String password, String name) { return new User(false, username, password, name); }

    public UserDto.SignupResDto toSignupResDto() { return UserDto.SignupResDto.builder().id(getId()).build(); }
}
