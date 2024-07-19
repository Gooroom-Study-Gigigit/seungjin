package com.project.doongdoong.domain.counsel.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

import static com.project.doongdoong.global.exception.ErrorType.NotFound.COUNSEL_NOT_FOUND;

public class CounselNotFoundException extends CustomException.NotFoundException {
    public CounselNotFoundException() {
        super(COUNSEL_NOT_FOUND, "해당 상담이 존재하지 않습니다.");
    }
}
