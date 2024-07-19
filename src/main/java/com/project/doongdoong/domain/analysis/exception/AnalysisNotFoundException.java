package com.project.doongdoong.domain.analysis.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class AnalysisNotFoundException extends CustomException.NotFoundException {
    public AnalysisNotFoundException() {
        super(ErrorType.NotFound.ANALYSIS_NOT_FOUND, "해당 분석은 존재하지 않습니다.");
    }
}
