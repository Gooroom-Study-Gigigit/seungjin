package com.project.doongdoong.domain.analysis.dto.response;

import lombok.*;
import org.checkerframework.checker.units.qual.A;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FellingStateCreateResponse {

    private String transcribedText;
    private double feelingState;
}
