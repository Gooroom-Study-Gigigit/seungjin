package com.project.doongdoong.global.common;

import com.project.doongdoong.global.exception.CustomException;
import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {
    private int errorCode;
    private String message;
    private List<String> details;

    public ErrorResponse(CustomException ex, List<String> messages) {
        this.errorCode = ex.getErrorCode();
        this.message = ex.getDetail();
        this.details = messages;
    }

}
