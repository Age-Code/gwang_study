package org.example.myblog.dto;

import lombok.*;
import org.example.myblog.domain.User;

public class UserDto {

    // Signup Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupReqDto {
        public String username;
        public String password;
        public String name;

        public User toEntity() { return User.of(getUsername(), getPassword(), getName()); }
    }

    // Signup Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class SignupResDto {
        Long id;
    }

    // Login Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginReqDto {
        public String username;
        public String password;
    }

    // Login Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class LoginResDto {
        public Long id;
    }
}
