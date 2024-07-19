package com.project.doongdoong.domain.counsel.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class CounselAlreadyProcessedException extends CustomException.ConflictException {
    public CounselAlreadyProcessedException() {
        super(ErrorType.Conflict.COUNSEL_ALREADY_EXIST, "해당 분석은 이미 상담 처리됐습니다.");
    }
}
