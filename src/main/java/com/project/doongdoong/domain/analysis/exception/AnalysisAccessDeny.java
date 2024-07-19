package com.project.doongdoong.domain.analysis.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class AnalysisAccessDeny extends CustomException.ForbiddenException {
    public AnalysisAccessDeny() {
        super(ErrorType.Forbidden.ANALYSIS_ACCESS_DENIED, "해당 사용자의 분석이 아니거나 존재하지 않는 분석입니다.");
    }
}
