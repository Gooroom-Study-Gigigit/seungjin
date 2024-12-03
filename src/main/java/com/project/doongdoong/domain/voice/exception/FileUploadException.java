package com.project.doongdoong.domain.voice.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class FileUploadException extends CustomException.ServerErrorException {
    public FileUploadException(ErrorType errorType, String detail) {
        super(errorType, detail);
    }
}
