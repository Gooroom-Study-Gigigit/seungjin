package com.project.doongdoong.domain.answer.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerCreateRequestDto {

    @NotNull(message = "questionId는 필수 입력 값입니다.")
    private Long questionId;

}
