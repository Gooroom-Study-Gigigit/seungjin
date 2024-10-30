package com.project.doongdoong.docs.analysis;

import com.project.doongdoong.docs.RestDocsSupport;
import com.project.doongdoong.domain.analysis.controller.AnalysisController;
import com.project.doongdoong.domain.analysis.dto.response.AnalysisCreateResponseDto;
import com.project.doongdoong.domain.analysis.service.AnalysisService;
import com.project.doongdoong.domain.answer.service.AnswerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnalysisControllerDocsTest extends RestDocsSupport {

    private final AnalysisService analysisService = Mockito.mock(AnalysisService.class);
    private final AnswerService answerService = Mockito.mock(AnswerService.class);

    @Override
    protected Object initController() {
        return new AnalysisController(analysisService, answerService);
    }


    @Test
    @DisplayName("분석을 위한 감정 분석 설문지를 생성한다.")
    //@WithMockUser(username = "APPLE_whffkaos007@naver.com")
    void createAnalysis() throws Exception {
        //given
        AnalysisCreateResponseDto result = AnalysisCreateResponseDto.builder()
                .analysisId(1L)
                .questionIds(List.of(1L, 2L, 3L, 4L))
                .questionTexts(List.of("1번째 질문", "2번째 질문", "3번째 질문", "4번째 질문"))
                .accessUrls(List.of("url1", "url2", "url3", "url4"))
                .build();
        BDDMockito.given(analysisService.createAnalysis(any()))
                .willReturn(result);

        // when & then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/analysis")
                                .header("Authorization", "Bearer AAAAA.BBBBBBB.CCCCCC")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("analysis-create",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer Token을 통한 인증"),
                                        headerWithName("Content-Type")
                                                .description("요청 컨텐트 타입")
                                ),
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER)
                                                .description("코드"),
                                        fieldWithPath("state").type(JsonFieldType.STRING)
                                                .description("상태"),
                                        fieldWithPath("message").type(JsonFieldType.NULL)
                                                .description("메세지"),
                                        fieldWithPath("data").type(JsonFieldType.OBJECT)
                                                .description("응답 데이터"),
                                        fieldWithPath("data.analysisId").type(JsonFieldType.NUMBER)
                                                .description("분석 ID"),
                                        fieldWithPath("data.questionIds").type(JsonFieldType.ARRAY)
                                                .description("질문 ID 목록"),
                                        fieldWithPath("data.questionTexts").type(JsonFieldType.ARRAY)
                                                .description("분석 질문 목록"),
                                        fieldWithPath("data.questionTexts").type(JsonFieldType.ARRAY)
                                                .description("분석 ID"),
                                        fieldWithPath("data.accessUrls").type(JsonFieldType.ARRAY)
                                                .description("음성 질문 접근 URL 목록")
                                )
                        )
                );
    }
}
