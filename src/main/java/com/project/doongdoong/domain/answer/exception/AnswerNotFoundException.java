package com.project.doongdoong.domain.answer.exception;

import com.project.doongdoong.global.exception.CustomException;

import static com.project.doongdoong.global.exception.ErrorType.NotFound.ANSWER_NOT_FOUND;

public class AnswerNotFoundException extends CustomException.NotFoundException {
    public AnswerNotFoundException() {
        super(ANSWER_NOT_FOUND, "답변이 존재하지 않습니다.");
    }
}
