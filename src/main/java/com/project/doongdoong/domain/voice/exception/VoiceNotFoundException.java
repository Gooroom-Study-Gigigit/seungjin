package com.project.doongdoong.domain.voice.exception;

import com.project.doongdoong.global.exception.CustomException;
import com.project.doongdoong.global.exception.ErrorType;

public class VoiceNotFoundException extends CustomException.NotFoundException {
    public VoiceNotFoundException() {
        super(ErrorType.NotFound.VOICE_NOT_FOUND, "해당 음성 파일이 존재하지 않습니다.");
    }
}
