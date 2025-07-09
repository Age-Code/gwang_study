package org.example.myblog.dto;

import lombok.*;

public class RefreshTokenDto {

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

}