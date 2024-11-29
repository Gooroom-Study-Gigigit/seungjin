package com.project.doongdoong.domain.counsel.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CounselCreateRequest {

    private Long analysisId;
    @NotBlank(message = "카테코리는 필수입니다.")
    private String counselType;
    @NotBlank(message = "상담 질문은 필수입니다.")
    private String question;

    @Builder
    public CounselCreateRequest(Long analysisId, String counselType, String question) {
        this.analysisId = analysisId;
        this.counselType = counselType;
        this.question = question;
    }

    @Builder
    public CounselCreateRequest(String counselType, String question) {
        this.counselType = counselType;
        this.question = question;
    }
}
