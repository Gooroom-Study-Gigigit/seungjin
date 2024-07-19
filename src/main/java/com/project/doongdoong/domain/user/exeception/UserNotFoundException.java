package com.project.doongdoong.domain.user.exeception;

import com.project.doongdoong.global.exception.CustomException;

import static com.project.doongdoong.global.exception.ErrorType.NotFound.*;

public class UserNotFoundException extends CustomException.NotFoundException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND, "해당 사용자는 존재하지 않습니다.");
    }
}
