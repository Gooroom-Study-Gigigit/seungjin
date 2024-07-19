package com.project.doongdoong.domain.user.dto;

import com.project.doongdoong.domain.user.model.SocialType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Principal {
    private String email;
    private SocialType socialType;

    public static Principal of(String email, SocialType socialType){
        return new Principal(email, socialType);
    }
}
