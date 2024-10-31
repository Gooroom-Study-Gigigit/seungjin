package com.project.doongdoong.docs.analysis;

import com.project.doongdoong.docs.RestDocsSupport;
import com.project.doongdoong.domain.analysis.controller.AnalysisController;
import com.project.doongdoong.domain.analysis.dto.response.*;
import com.project.doongdoong.domain.analysis.service.AnalysisService;
import com.project.doongdoong.domain.answer.dto.AnswerCreateResponseDto;
import com.project.doongdoong.domain.answer.service.AnswerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Test
    @DisplayName("본인의 분석 정보 하나를 조회한다.")
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
                        RestDocumentationRequestBuilders.get("/api/v1/analysis/{id}", anyLong())
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer AAAAA.BBBBBBB.CCCCCC")
                ).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("analysis-find",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName("Authorization")
                                                .description("Bearer Token 을 통한 인증"),
                                        headerWithName("Content-Type")
                                                .description("요청 컨텐트 타입")
                                ),
                                pathParameters(
                                        parameterWithName("id")
                                                .description("조회할 분석 리소스의 ID")
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
                                                fieldWithPath("data.time").type(JsonFieldType.STRING)
                                                        .description("분석한 시간"),
                                                fieldWithPath("data.feelingState").type(JsonFieldType.NUMBER)
                                                        .description("분석 결과 점수"),
                                                fieldWithPath("data.questionIds").type(JsonFieldType.ARRAY)
                                                        .description("분석 질문 ID 목록"),
                                                fieldWithPath("data.questionContent").type(JsonFieldType.ARRAY)
                                                        .description("음성 질문 내용 목록"),
                                                fieldWithPath("data.questionContentVoiceUrls").type(JsonFieldType.ARRAY)
                                                    .description("분석 질문 음성 접근 url 목록"),
                                                fieldWithPath("data.answerContent").type(JsonFieldType.ARRAY)
                                                    .description("분석 질문에 대한 답변 내용 목록")
                                        )
                                )
                );
    }
/*
    @Test
    @DisplayName("본인의 분석 결과들을 페이징 조회한다.")
    @WithMockUser(username = "APPLE_whffkaos007@naver.com")
    void getAnalysisList() throws Exception {
        //given
        AnaylsisResponseDto detailResult = AnaylsisResponseDto.builder()
                .analysisId(1L)
                .time("test time")
                .feelingState(30.5)
                .questionContent(List.of("질문1", "질문2", "질문3", "질문4"))
                .build();

        AnaylsisListResponseDto result = AnaylsisListResponseDto.builder()
                .pageNumber(1)
                .totalPage(1)
                .anaylsisResponseDtoList(List.of(detailResult))
                .build();

        when(analysisService.getAnalysisList(anyString(), anyInt()))
                .thenReturn(result);
        // when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/analysis")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNumber", "1")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.pageNumber").value(result.getPageNumber()))
                .andExpect(jsonPath("$.data.totalPage").value(result.getTotalPage()))
                .andExpect(jsonPath("$.data.anaylsisResponseDtoList").isArray());
    }

    @Test
    @DisplayName("최근 마지막 분석 일을 기준으로 일주일 간 감정 수치 정보를 가져온다.")
    @WithMockUser
    void getAnalysesGroupByDay() throws Exception {
        //given
        String exampleDate = "yyyy:MM:dd hh:mm";
        FeelingStateResponseListDto result = FeelingStateResponseListDto.builder()
                .feelingStateResponsesDto(List.of(
                        ofFellingStateResponseDto(exampleDate, 30.0)
                        , ofFellingStateResponseDto(exampleDate, 40.0)
                        , ofFellingStateResponseDto(exampleDate, 50.0))
                )
                .build();

        when(analysisService.getAnalysisListGroupByDay(anyString()))
                .thenReturn(result);

        //when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/analysis/week")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.feelingStateResponsesDto").isArray());

    }

    private static FeelingStateResponseDto ofFellingStateResponseDto(String exampleDate, double stateScore) {
        return FeelingStateResponseDto.builder()
                .date(exampleDate)
                .avgFeelingState(stateScore)
                .build();
    }

    @Test
    @DisplayName("각 질문에 대한 답볍인 음성 파일과 음성 파일 기반 텍스트를 통해 사용자의 감정 상태를 수치로 나타냅니다.")
    @WithMockUser
    void analyzeEmotion() throws Exception {
        //given
        double exampleScore = 45.5;
        Long exampleId = 1L;
        FellingStateCreateResponse result = FellingStateCreateResponse.builder()
                .feelingState(exampleScore)
                .build();

        when(analysisService.analyzeEmotion(anyLong(), anyString()))
                .thenReturn(result);
        //when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/analysis/{id}", exampleId)
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.feelingState").value(result.getFeelingState()));
    }
    @Test
    @DisplayName("분석을 위해 제공한 질문들 중 하나의 질문에 대한 음성 파일인 답변을 저장한다.")
    @WithMockUser
    void createAnswer() throws Exception {
        //given
        Long exampleAnalysisId = 1L;
        Long exampleAnswerId = 1L;
        AnswerCreateResponseDto result = AnswerCreateResponseDto.builder()
                .answerId(exampleAnswerId)
                .build();

        MockMultipartFile exampleAudioFile = new MockMultipartFile(
                "file",
                "testAudio.mp3",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "실제는 음성 데이터야 하지만 테스트 편의성을 위해 텍스트에 대한 바이트 정보 제공".getBytes()
        );

        when(answerService.createAnswer(exampleAnalysisId, exampleAudioFile, exampleAnswerId))
                .thenReturn(result);

        //when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/api/v1/analysis/{id}/answer", exampleAnalysisId)
                                .file(exampleAudioFile)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .param("questionId", String.valueOf(exampleAnswerId))
                                .with(csrf())
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.answerId").value(result.getAnswerId()));

    }

    @Test
    @DisplayName("분석을 위해 제공한 질문들 중 하나의 질문에 대한 음성 파일이 비어있거나 없다면 저장하지 않습니다.")
    @WithMockUser
    void createAnswerException() throws Exception {
        //given
        Long exampleAnalysisId = 1L;
        Long exampleAnswerId = 1L;
        AnswerCreateResponseDto result = AnswerCreateResponseDto.builder()
                .answerId(exampleAnswerId)
                .build();

        MockMultipartFile exampleAudioFile = new MockMultipartFile(
                "file",
                "testAudio.mp3",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                new byte[0]
        );


        //when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.multipart("/api/v1/analysis/{id}/answer", exampleAnalysisId)
                                .file(exampleAudioFile)
                                .contentType(MediaType.MULTIPART_FORM_DATA)
                                .param("questionId", String.valueOf(exampleAnswerId))
                                .with(csrf())
                ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value(1))
                .andExpect(jsonPath("$.message").value("파일 자료가 하나도 없습니다."));

    }

    @Test
    @DisplayName("해당하는 분석 관련 정보를 삭제한다.")
    @WithMockUser
    void deleteAnalysis() throws Exception {
        //given, when, then
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/analysis/{id}", anyLong())
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(csrf())
                ).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.data").isEmpty());
    }*/
}



