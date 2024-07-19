package com.project.doongdoong.domain.answer.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class AnswerConflictException extends CustomException.ConflictException {
    public AnswerConflictException() {
        super(ErrorType.Conflict.ANSWER_ALREADY_CREATED, "이미 질문에 대한 답변이 존재합니다.");
    }
}
