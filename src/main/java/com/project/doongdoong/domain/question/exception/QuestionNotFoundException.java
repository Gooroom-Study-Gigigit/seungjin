package com.project.doongdoong.domain.question.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class QuestionNotFoundException extends CustomException.NotFoundException {
    public QuestionNotFoundException() {
        super(ErrorType.NotFound.QUESTION_NOT_FOUND,"해당 질문은 존재하지 않습니다.");
    }
}
