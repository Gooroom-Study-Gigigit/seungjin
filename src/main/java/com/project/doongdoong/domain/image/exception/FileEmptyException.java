package com.project.doongdoong.domain.image.exception;

import com.project.doongdoong.global.exception.CustomException;

import static com.project.doongdoong.global.exception.ErrorType.BadRequest.FILE_EMPTY;

public class FileEmptyException extends CustomException.InvalidRequestException {
    public FileEmptyException() {
        super(FILE_EMPTY, "파일 자료가 하나도 없습니다.");
    }
}
