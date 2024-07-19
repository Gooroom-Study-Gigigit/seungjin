package com.project.doongdoong.domain.analysis.service;

import com.project.doongdoong.domain.analysis.dto.response.*;

public interface AnalysisService {

    public AnalysisCreateResponseDto createAnalysis(String uniqueValue);

    public AnalysisDetailResponse getAnalysis(Long analysisId);

    public AnaylsisListResponseDto getAnalysisList(String uniqueValue, int pageNumber);

    public FeelingStateResponseListDto getAnalysisListGroupByDay(String uniqueValue);

    public FellingStateCreateResponse analyzeEmotion(Long analysisId, String uniqueValue);

    public void removeAnaylsis(Long analysisId);
}
