package com.project.doongdoong.global.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ReissueDto {
    @NotBlank(message = "refreshToken이 비어 있습니다.")
    private String refreshToken;

}
