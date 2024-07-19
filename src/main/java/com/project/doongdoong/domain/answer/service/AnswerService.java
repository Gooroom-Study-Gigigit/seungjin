package com.project.doongdoong.domain.answer.service;


import com.project.doongdoong.domain.answer.dto.AnswerCreateRequestDto;
import com.project.doongdoong.domain.answer.dto.AnswerCreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface AnswerService {
    //public AnswerCreateResponseDto createAnswer(Long anaylsisId, MultipartFile multipartFile, AnswerCreateRequestDto dto);
    public AnswerCreateResponseDto createAnswer(Long anaylsisId, MultipartFile multipartFile, Long questionId);
}
