package com.project.doongdoong.domain.counsel.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CounselDetailResponse {

    private String date;
    private Long counselId;
    private String question;
    private String answer;
    private String imageUrl;
    private String counselType;

    @Builder
    public CounselDetailResponse(String data, Long counselId, String question, String answer, String imageUrl, String counselType) {
        this.date = data;
        this.counselId = counselId;
        this.question = question;
        this.answer = answer;
        this.imageUrl = imageUrl;
        this.counselType = counselType;
    }

}
