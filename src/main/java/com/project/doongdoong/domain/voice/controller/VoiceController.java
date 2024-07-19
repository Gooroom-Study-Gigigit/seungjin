package com.project.doongdoong.domain.voice.controller;

import com.project.doongdoong.domain.voice.dto.request.VoiceSaveRequestDto;
import com.project.doongdoong.domain.voice.service.VoiceServiceImp;
import com.project.doongdoong.global.common.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/voice")
@RequiredArgsConstructor
public class VoiceController {
    private final VoiceServiceImp voiceService;

    @PostMapping
    public ApiResponse<?> uploadVoices(@Valid @ModelAttribute VoiceSaveRequestDto dto){

        return ApiResponse.of(HttpStatus.OK, null, voiceService.saveVoices(dto));
    }
}
