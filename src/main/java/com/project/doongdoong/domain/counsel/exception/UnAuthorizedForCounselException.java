package com.project.doongdoong.domain.counsel.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class UnAuthorizedForCounselException extends CustomException.UnauthorizedException {
    public UnAuthorizedForCounselException() {
        super(ErrorType.Unauthorized.UNAUTHORIZED_COUNSEL, "다른 사용자의 상담입니다.");
    }
}
