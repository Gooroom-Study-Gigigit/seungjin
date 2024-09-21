package com.project.doongdoong.domain.analysis.controller;

import com.project.doongdoong.domain.analysis.dto.response.AnalysisCreateResponseDto;
import com.project.doongdoong.domain.analysis.dto.response.AnalysisDetailResponse;
import com.project.doongdoong.module.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnalysisControllerTest extends ControllerTestSupport {

    @Test
    @DisplayName("분석을 위한 감정 분석 설문지를 생성한다.")
    @WithMockUser(username = "APPLE_whffkaos007@naver.com")
    void createAnaysis() throws Exception {
        //given
        String uniqueValue = "APPLE_whffkaos007@naver.com";

        AnalysisCreateResponseDto result = AnalysisCreateResponseDto.builder()
                .analysisId(1L)
                .questionIds(List.of(1L, 2L, 3L, 4L))
                .questionTexts(List.of("1번째 질문", "2번째 질문", "3번째 질문", "4번째 질문"))
                .accessUrls(List.of("url1", "url2", "url3", "url4"))
                .build();

        when(analysisService.createAnalysis(uniqueValue))
                .thenReturn(result);

        // when & then
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/analysis")
                        .with(csrf())
                        //.header("Authorization", "Bearer AAAAA.BBBBBBB.CCCCCC")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.analysisId").value(result.getAnalysisId()))
                .andExpect(jsonPath("$.data.questionIds[0]").value(1L))
                .andExpect(jsonPath("$.data.questionIds[1]").value(2L))
                .andExpect(jsonPath("$.data.questionIds[2]").value(3L))
                .andExpect(jsonPath("$.data.questionIds[3]").value(4L))
                .andExpect(jsonPath("$.data.questionTexts[0]").value("1번째 질문"))
                .andExpect(jsonPath("$.data.questionTexts[1]").value("2번째 질문"))
                .andExpect(jsonPath("$.data.questionTexts[2]").value("3번째 질문"))
                .andExpect(jsonPath("$.data.questionTexts[3]").value("4번째 질문"))
                .andExpect(jsonPath("$.data.accessUrls[0]").value("url1"))
                .andExpect(jsonPath("$.data.accessUrls[1]").value("url2"))
                .andExpect(jsonPath("$.data.accessUrls[2]").value("url3"))
                .andExpect(jsonPath("$.data.accessUrls[3]").value("url4"));

        Mockito.verify(analysisService, times(1)).createAnalysis(uniqueValue);

    }

    @Test
    @DisplayName("본인의 분석 정보 하나를 조회한다.")
    @WithMockUser(username = "APPLE_whffkaos007@naver.com")
    void getAnalysis() throws Exception {
        //given
        AnalysisDetailResponse result = AnalysisDetailResponse.builder()
                .analysisId(1L)
                .time("yyyy:MM:dd XX:XX")
                .feelingState(59.0)
                .questionIds(List.of(1L, 2L, 3L, 4L))
                .questionContent(List.of("질문1", "질문2", "질문3", "질문4"))
                .questionContentVoiceUrls(List.of("url1", "url2", "url3", "url4"))
                .answerContent(List.of("답변1", "답변2", "답변3", "답변4"))
                .build();

        when(analysisService.getAnalysis(anyLong()))
                .thenReturn(result);

        // when, then
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/analysis/{id}", anyLong())
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.analysisId").value(result.getAnalysisId()))
                .andExpect(jsonPath("$.data.time").value(result.getTime()))
                .andExpect(jsonPath("$.data.feelingState").value(result.getFeelingState()));
        // 리스트 형태 값은 생략

        verify(analysisService, times(1)).getAnalysis(anyLong());

    }


}
