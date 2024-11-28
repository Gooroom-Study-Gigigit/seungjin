package com.project.doongdoong.domain.analysis.dto.response;

import com.project.doongdoong.domain.analysis.model.Analysis;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class AnalysisListResponseDto {
    private int pageNumber;
    private int totalPage;
    private List<AnaylsisResponseDto> analysisResponseDtoList;

    @Builder
    private AnalysisListResponseDto(int pageNumber, int totalPage, List<AnaylsisResponseDto> analysisResponseDtoList) {
        this.pageNumber = pageNumber;
        this.totalPage = totalPage;
        this.analysisResponseDtoList = analysisResponseDtoList;
    }

    public static AnalysisListResponseDto of(Page<Analysis> analysisPages, DateTimeFormatter formatter) {
        return AnalysisListResponseDto.builder()
                .pageNumber(analysisPages.getNumber() + 1)
                .totalPage(analysisPages.getTotalPages())
                .analysisResponseDtoList(analysisPages.getContent().stream()
                        .map(analysis -> AnaylsisResponseDto.builder()
                                .analysisId(analysis.getId())
                                .time(analysis.getCreatedTime().format(formatter))
                                .feelingState(analysis.getFeelingState())
                                .questionContent(analysis.getQuestions().stream()
                                        .map(a -> a.getQuestionContent().getText())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }


}
