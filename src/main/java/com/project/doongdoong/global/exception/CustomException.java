package com.project.doongdoong.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    int errorCode;
    ErrorType errorType;
    String detail;

    public CustomException(ErrorType errorType, String detail) {
        super(detail);
        this.errorCode = errorType.getCode();
        this.errorType = errorType;
        this.detail = detail;
    }

    public abstract static class InvalidRequestException extends CustomException {
        public InvalidRequestException(ErrorType.BadRequest errorType, String detail) {
            super(errorType, detail);
        }
    }

    public abstract static class UnauthorizedException extends CustomException {
        public UnauthorizedException(ErrorType.Unauthorized errorType, String detail) {
            super(errorType, detail);
        }
    }

    public abstract static class ForbiddenException extends CustomException {
        public ForbiddenException(ErrorType.Forbidden errorType, String detail) {
            super(errorType, detail);
        }
    }

    public abstract static class NotFoundException extends CustomException {
        public NotFoundException(ErrorType.NotFound errorType, String detail) {
            super(errorType, detail);
        }
    }

    public abstract static class ConflictException extends CustomException {
        public ConflictException(ErrorType.Conflict errorType, String detail) {
            super(errorType, detail);
        }
    }

    public abstract static class ServerErrorException extends CustomException {
        public ServerErrorException(ErrorType errorType, String detail) {
            super(errorType, detail);
        }
    }
}
