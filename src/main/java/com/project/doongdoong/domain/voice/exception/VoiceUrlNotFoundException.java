package com.project.doongdoong.domain.voice.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class VoiceUrlNotFoundException extends CustomException.NotFoundException {
    public VoiceUrlNotFoundException() {
        super(ErrorType.NotFound.VOICE_URL_NOT_FOUND, "해당 음성 파일 uri이 존재하지 않습니다.");
    }
}
