package com.project.doongdoong.domain.analysis.service;

import com.project.doongdoong.domain.analysis.dto.request.AnalysisEmotionRequestDto;
import com.project.doongdoong.domain.analysis.dto.response.*;

public interface AnalysisService {

    public AnalysisCreateResponseDto createAnalysis(String uniqueValue);

    public AnalysisDetailResponse getAnalysis(Long analysisId);

    public AnaylsisListResponseDto getAnalysisList(String uniqueValue, int pageNumber);

    public FeelingStateResponseListDto getAnalysisListGroupByDay(String uniqueValue);

    public Object analyzeEmotion(Long analysisId, AnalysisEmotionRequestDto dto);

}
