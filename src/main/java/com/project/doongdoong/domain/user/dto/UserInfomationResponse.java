package com.project.doongdoong.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfomationResponse {
    private String nickname;

    private String email;

    private String socialType;

    private Long analysisCount;


    @Builder
    public UserInfomationResponse(String nickname, String email, String socialType, Long analysisCount) {
        this.nickname = nickname;
        this.email = email;
        this.socialType = socialType;
        this.analysisCount = analysisCount;
    }
}
