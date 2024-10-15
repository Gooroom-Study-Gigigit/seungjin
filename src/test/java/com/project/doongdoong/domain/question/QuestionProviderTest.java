package com.project.doongdoong.domain.question;

import com.project.doongdoong.domain.IntegrationSupportTest;
import com.project.doongdoong.domain.question.model.Question;
import com.project.doongdoong.domain.question.service.QuestionProvidable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionProviderTest extends IntegrationSupportTest {

    @Autowired
    QuestionProvidable questionProvider;

    @Test
    @DisplayName("임의로 고정 질문과 비고정 질문을 섞은 질문지를 제공한다.")
    void createRandomQuestions() {
        //given
        int expectedTotalSize = 4;
        //when
        List<Question> randomQuestions = questionProvider.createRandomQuestions();
        //then
        assertThat(randomQuestions)
                .hasSize(expectedTotalSize)
                .extracting(question -> question.getQuestionContent().isFixedQuestion())
                .containsExactly(true, true, false, false);

    }


}
