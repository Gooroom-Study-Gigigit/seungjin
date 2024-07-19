package com.project.doongdoong.domain.analysis.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class AlreadyAnalyzedException extends CustomException.ConflictException {

    public AlreadyAnalyzedException() {
        super(ErrorType.Conflict.ANALYSIS_ALREADY_ANALYZE, "이미 분석했습니다.");
    }
}
