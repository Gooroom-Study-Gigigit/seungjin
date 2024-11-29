package com.project.doongdoong.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInformationResponseDto {
    private String nickname;

    private String email;

    private String socialType;

    private Long analysisCount;


    @Builder
    public UserInformationResponseDto(String nickname, String email, String socialType, Long analysisCount) {
        this.nickname = nickname;
        this.email = email;
        this.socialType = socialType;
        this.analysisCount = analysisCount;
    }
}
