package com.project.doongdoong.domain.image.exception;

import com.project.doongdoong.global.exception.CustomException;

import static com.project.doongdoong.global.exception.ErrorType.ServerError.FILE_DELETE_FAIL;

public class FileDeleteException extends CustomException.ServerErrorException {
    public FileDeleteException() {
        super(FILE_DELETE_FAIL, "이미지 삭제 오류가 발생했습니다.");
    }
}
