package com.project.doongdoong.domain.user.exeception;

import com.project.doongdoong.global.exception.CustomException;

import static com.project.doongdoong.global.exception.ErrorType.Forbidden.TOKEN_INFO_FORBIDDEN;

public class TokenInfoFobiddenException extends CustomException.ForbiddenException {
    public TokenInfoFobiddenException() {
        super(TOKEN_INFO_FORBIDDEN, "act과 rft 간 정보가 일치하지 않습니다.");
    }
}
