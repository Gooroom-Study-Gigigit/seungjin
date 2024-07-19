package com.project.doongdoong.global.dto.response;

import lombok.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;


    public static TokenDto of(String act, String rft){
        return TokenDto.builder()
                .accessToken(act)
                .refreshToken(rft)
                .build();
    }
}
