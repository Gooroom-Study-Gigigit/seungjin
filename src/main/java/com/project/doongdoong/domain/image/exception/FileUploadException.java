package com.project.doongdoong.domain.image.exception;

import com.project.doongdoong.global.exception.CustomException;

import static com.project.doongdoong.global.exception.ErrorType.ServerError.FILE_UPLOAD_FAIL;

public class FileUploadException extends CustomException.ServerErrorException {
    public FileUploadException() {
        super(FILE_UPLOAD_FAIL, "파일 업로드에 실패했습니다.");
    }
}
