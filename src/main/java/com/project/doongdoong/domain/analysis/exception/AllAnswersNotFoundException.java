package com.project.doongdoong.domain.analysis.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class AllAnswersNotFoundException extends CustomException.NotFoundException {

    public AllAnswersNotFoundException() {
        super(ErrorType.NotFound.ALL_ANSWER_NOT_FOUND, "질문에 해당하는 모든 답변이 존재하지 않습니다.");
    }
}
