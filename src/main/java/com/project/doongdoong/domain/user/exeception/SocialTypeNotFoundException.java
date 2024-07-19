package com.project.doongdoong.domain.user.exeception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class SocialTypeNotFoundException extends CustomException.NotFoundException {
    public SocialTypeNotFoundException() {
        super(ErrorType.NotFound.SOCIAL_TYPE_NOT_FOUND, "해당 소셜 타입은 존재하지 않습니다.");
    }
}
