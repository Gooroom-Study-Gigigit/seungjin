package com.project.doongdoong.domain.image.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
public class ImageDeleteRequestDto {
    @Schema(description = "이미지 경로는 필수입니다.")
    private List<@NotBlank @URL(message = "이미지 경로가 비어 있습니다.") String> urls;
}
