package com.project.doongdoong.domain.question.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class NoMatchingQuestionException extends CustomException.InvalidRequestException {
    public NoMatchingQuestionException() {
        super(ErrorType.BadRequest.NO_MATCHING_QUESTION, "분석에 해당하는 질문이 아닙니다.");
    }
}
