package com.project.doongdoong.domain.question.model;

import com.project.doongdoong.domain.question.model.QuestionContent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


class QuestionContentTest {
    @Test
    @DisplayName("고정 QuestionContent을 랜덤으로 하나 반환한다. ")
    void provideFixedQuestionContent(){
        //given
        List<QuestionContent> values = QuestionContent.getFixedQuestionContents();
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
        List<QuestionContent> values = QuestionContent.getUnFixedQuestionContents();
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