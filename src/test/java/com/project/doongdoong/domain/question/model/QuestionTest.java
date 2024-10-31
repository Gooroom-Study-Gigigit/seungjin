package com.project.doongdoong.domain.question.model;

import com.project.doongdoong.domain.answer.model.Answer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuestionTest {
    @DisplayName("해당 질문에는 답변 정보가 있다.")
    @Test
    void hasAnswer() {
        // given
        Question question = Question.of(QuestionContent.FIXED_QUESTION1);
        Answer answer = Answer.builder()
                .build();

        question.connectAnswer(answer);

        // when
        boolean result = question.hasAnswer();
        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("해당 질문에는 답변 정보가 없다.")
    @Test
    void hasAnswerException() {
        // given
        Question question = Question.of(QuestionContent.FIXED_QUESTION1);
        /**
         * 하나의 검증에 행위가 2개가 있어 좋지는 않아 보임.
         * 하지만 직접 필드에 접근 하는 거보다 이러한 이펙트가 더 나아 보임
         */
        question.connectAnswer(null);

        // when
        boolean result = question.hasAnswer();
        // then
        Assertions.assertThat(result).isFalse();
    }

}