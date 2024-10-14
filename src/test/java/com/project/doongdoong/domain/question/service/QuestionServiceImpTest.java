package com.project.doongdoong.domain.question.service;

import com.project.doongdoong.domain.question.model.QuestionContent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


class QuestionServiceImpTest {
    @Test
    @DisplayName("고정 QuestionContent을 랜덤으로 하나 반환한다. ")
    void provideFixedQuestionContent(){
        //given
        List<QuestionContent> values = List.of(
                QuestionContent.FIXED_QUESTION1
                , QuestionContent.FIXED_QUESTION2
                , QuestionContent.FIXED_QUESTION3
                , QuestionContent.FIXED_QUESTION4
                , QuestionContent.FIXED_QUESTION5
                , QuestionContent.FIXED_QUESTION6
        );
        Random random = new Random();
        int index = random.nextInt(values.size());
        boolean isFixed = values.get(index).isFixedQuestion();
        //when
        QuestionContent fixedQuestionContent = QuestionContent.provideRandomFixedQuestionContent();
        //then
        assertThat(isFixed).isEqualTo(true);
        assertThat(values).contains(fixedQuestionContent);

    }

    @Test
    @DisplayName("고정되지 않은 QuestionContent을 랜덤으로 하나 반환한다. ")
    void provideUnFixedQuestionContent(){
        //give
        List<QuestionContent> values = List.of(
                QuestionContent.UNFIXED_QUESTION1
                , QuestionContent.UNFIXED_QUESTION2
                , QuestionContent.UNFIXED_QUESTION3
                , QuestionContent.UNFIXED_QUESTION4
        );
        Random random = new Random();
        int index = random.nextInt(values.size());
        boolean isFixed = values.get(index).isFixedQuestion();
        //when
        QuestionContent unFixedQuestionContent = QuestionContent.provideRandomUnFixedQuestionContent();
        //then
        assertThat(isFixed).isEqualTo(false);
        assertThat(values).contains(unFixedQuestionContent);
    }

}