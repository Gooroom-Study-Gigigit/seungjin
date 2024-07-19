package com.project.doongdoong.domain.voice.service;

import com.project.doongdoong.domain.question.model.QuestionContent;
import com.project.doongdoong.domain.voice.dto.request.VoiceSaveRequestDto;
import com.project.doongdoong.domain.voice.dto.response.VoiceDetailResponseDto;
import com.project.doongdoong.domain.voice.dto.response.VoicesResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VoiceService {
    public VoicesResponseDto saveVoices(VoiceSaveRequestDto saveDto);

    public VoiceDetailResponseDto saveVoice(MultipartFile multipartFile);

    public void deleteVoice(String imageUrl);

    public void deleteVoices(List<String> voiceUrls);

    public VoiceDetailResponseDto saveTtsVoice(byte[] bytes, String originName, QuestionContent questionContent);

}
