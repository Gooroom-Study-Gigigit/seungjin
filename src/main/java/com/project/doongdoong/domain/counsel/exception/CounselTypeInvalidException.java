package com.project.doongdoong.domain.counsel.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class CounselTypeInvalidException extends CustomException.InvalidRequestException {
    public CounselTypeInvalidException(String detail) {
        super(ErrorType.BadRequest.COUNSEL_TYPE_WRONG, "입력한 상담 타입은 존재하지 않습니다.");
    }
}
