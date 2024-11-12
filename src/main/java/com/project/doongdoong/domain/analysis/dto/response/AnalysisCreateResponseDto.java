package com.project.doongdoong.domain.analysis.dto.response;

import com.project.doongdoong.domain.analysis.model.Analysis;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.project.doongdoong.domain.analysis.model.QAnalysis.analysis;

@Getter
@NoArgsConstructor
public class AnalysisCreateResponseDto {

    private Long analysisId;
    private List<Long> questionIds;
    private List<String> questionTexts;
    private List<String> accessUrls;

    public static AnalysisCreateResponseDto of(Long analysisId, List<Long> questionIds, List<String> questionTexts, List<String> accessUrls){
        return AnalysisCreateResponseDto.builder()
                .analysisId(analysisId)
                .questionIds(questionIds)
                .questionTexts(questionTexts)
                .accessUrls(accessUrls)
                .build();
    }
    @Builder
    public AnalysisCreateResponseDto(Long analysisId, List<Long> questionIds, List<String> questionTexts, List<String> accessUrls){
        this.analysisId = analysisId;
        this.questionIds = questionIds;
        this.questionTexts = questionTexts;
        this.accessUrls = accessUrls;

    }
}
