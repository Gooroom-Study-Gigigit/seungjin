package com.project.doongdoong.domain.user.exeception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

import static com.project.doongdoong.global.exception.ErrorType.NotFound.REFRESH_TOKEN_NOT_FOUND;

public class RefreshTokenNoutFoundException extends CustomException.NotFoundException {
    public RefreshTokenNoutFoundException() {
        super(REFRESH_TOKEN_NOT_FOUND, "refreshToken이 존재하지 않아 토큰 갱신에 실패했습니다.");
    }
}
