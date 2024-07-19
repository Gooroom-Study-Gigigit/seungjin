package com.project.doongdoong.domain.counsel.service;


import com.project.doongdoong.domain.counsel.dto.request.CounselCreateRequest;
import com.project.doongdoong.domain.counsel.dto.response.CounselDetailResponse;
import com.project.doongdoong.domain.counsel.dto.response.CounselListResponse;
import com.project.doongdoong.domain.counsel.dto.response.CounselResultResponse;

public interface CounselService {
    public CounselResultResponse consult(String socialId, CounselCreateRequest request);

    public CounselDetailResponse findCouselContent(String socialId, Long counselId);

    public CounselListResponse findConusels(String uniqueValue, int pageNumber);
}
