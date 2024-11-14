package com.project.doongdoong.domain.analysis.dto.response;

import com.project.doongdoong.domain.analysis.model.Analysis;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AnalysisDetailResponse {
    private Long analysisId;
    private String time;
    private double feelingState;
    private List<Long> questionIds;
    private List<String> questionContent;
    private List<String> questionContentVoiceUrls;
    private List<String> answerContent;

    @Builder
    public AnalysisDetailResponse(Long analysisId, double feelingState, String time, List<String> questionContent
            , List<Long> questionIds, List<String> questionContentVoiceUrls, List<String> answerContent) {
        this.analysisId = analysisId;
        this.time = time;
        this.feelingState = feelingState;
        this.questionIds = questionIds;
        this.questionContent = questionContent;
        this.questionContentVoiceUrls = questionContentVoiceUrls;
        this.answerContent = answerContent;
    }

    public static AnalysisDetailResponse of(Long analysisId, double feelingState, String time, List<String> questionContent
            , List<Long> questionIds, List<String> questionContentVoiceUrls, List<String> answerContent) {
        return AnalysisDetailResponse.builder()
                .analysisId(analysisId)
                .time(time)
                .feelingState(feelingState)
                .questionIds(questionIds)
                .questionContent(questionContent)
                .questionContentVoiceUrls(questionContentVoiceUrls)
                .answerContent(answerContent)
                .build();
    }
}
