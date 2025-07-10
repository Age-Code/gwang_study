package org.example.myblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.myblog.domain.Permission;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class PermissionDto {

    // BaseDto
    @Getter @Setter @SuperBuilder
    @NoArgsConstructor @AllArgsConstructor
    public static class BaseDto {
        String empty;

        public BaseDto afterBuild(BaseDto baseDto) {
            BeanUtils.copyProperties(baseDto, this);

            return this;
        }
    }

    // Create Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto extends BaseDto {
        String title;
        String content;
    }

    // Create Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class CreateSevDto extends CreateReqDto {
        Long reqUserId;

        public Permission toEntity() { return Permission.of(getTitle(), getContent()); }
    }

    // Create Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    // Detail Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto extends BaseDto {
        Long id;
    }

    // Detail Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailSevDto extends DetailReqDto {
        Long reqUserId;
    }

    // Detail Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        String title;
        Long userId;
        LocalDateTime createdAt;
        LocalDateTime modifiedAt;
        Boolean deleted;
        String content;

        Boolean approved;
    }

    // List Request Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto {
        Boolean deleted;
    }

    // List Response Dto
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class ListResDto {
        Long id;
        String title;
        Long userId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDateTime createdAt;
    }

    // Update Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends BaseDto {
        Long id;
        String title;
        String content;
    }

    // Update Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateSevDto extends UpdateReqDto {
        Long reqUserId;
    }

    // Delete Request Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteReqDto extends BaseDto {
        Long id;
    }

    // Delete Service Dto
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteSevDto extends DeleteReqDto {
        Long reqUserId;
    }
}
