package com.project.doongdoong.domain.analysis.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnaylsisListResponseDto {
    private int pageNumber;
    private int totalPage;
    private List<AnaylsisResponseDto> analysisResponseDtoList;

}
