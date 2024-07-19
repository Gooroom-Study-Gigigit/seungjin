package com.project.doongdoong.domain.image.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

import static com.project.doongdoong.global.exception.ErrorType.NotFound.IMAGE_URL_NOT_FOUND;

public class ImageUrlNotFoundException extends CustomException.NotFoundException {
    public ImageUrlNotFoundException() {
        super(IMAGE_URL_NOT_FOUND, "해당 url은 존재하지 않습니다.");
    }
}
