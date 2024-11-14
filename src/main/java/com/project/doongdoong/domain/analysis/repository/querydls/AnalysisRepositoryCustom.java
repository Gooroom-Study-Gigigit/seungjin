package com.project.doongdoong.domain.analysis.repository.querydls;

import com.project.doongdoong.domain.analysis.model.Analysis;

import java.util.Optional;

public interface AnalysisRepositoryCustom {
    Optional<Analysis> searchAnalysisWithVoiceOfAnswer(Long analysisId);

    Optional<Analysis> searchFullAnalysisBy(Long analysisId);
}
