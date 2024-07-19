package com.project.doongdoong.domain.counsel.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CounselResponse {

    private String date;
    private Long counselId;
    private boolean isAnalysisUsed;
    private String counselType;

    @Builder
    public CounselResponse(String date, Long counselId, boolean isAnalysisUsed, String counselType) {
        this.date = date;
        this.counselId = counselId;
        this.isAnalysisUsed = isAnalysisUsed;
        this.counselType = counselType;
    }
}
