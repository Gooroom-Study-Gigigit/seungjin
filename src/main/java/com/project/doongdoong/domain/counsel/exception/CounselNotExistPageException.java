package com.project.doongdoong.domain.counsel.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class CounselNotExistPageException extends CustomException.NotFoundException {
    public CounselNotExistPageException() {
        super(ErrorType.NotFound.NOT_FOUND_DEFAULT, "존재하지 않는 페이지입니다.");
    }
}
