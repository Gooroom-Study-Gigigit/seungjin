package com.project.doongdoong.global.exception;

public class FileEmptyException extends CustomException.InvalidRequestException {

    public FileEmptyException(ErrorType.BadRequest errorType, String detail) {
        super(errorType, detail);
    }
}
