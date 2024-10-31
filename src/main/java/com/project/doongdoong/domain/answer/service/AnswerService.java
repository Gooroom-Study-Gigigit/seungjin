package com.project.doongdoong.domain.answer.service;


import com.project.doongdoong.domain.answer.dto.AnswerCreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface AnswerService {
    public AnswerCreateResponseDto createAnswer(Long analysisId, MultipartFile multipartFile, Long questionId);
}
