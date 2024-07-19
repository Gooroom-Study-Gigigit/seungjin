package com.project.doongdoong.domain.counsel.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CounselListResponse {

    private int currentPage;
    private int numberPerPage;
    private int totalPage;
    private long totalElements;
    private List<CounselResponse> counselContent;

    @Builder
    public CounselListResponse(int currentPage, int numberPerPage, int totalPage, long totalElements, List<CounselResponse> counselContent) {
        this.currentPage = currentPage;
        this.numberPerPage = numberPerPage;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
        this.counselContent = counselContent;
    }
}
