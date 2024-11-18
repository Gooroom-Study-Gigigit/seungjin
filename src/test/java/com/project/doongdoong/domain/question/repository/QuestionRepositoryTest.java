package com.project.doongdoong.domain.question.repository;

import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.analysis.repository.AnalysisRepository;
import com.project.doongdoong.domain.question.model.Question;
import com.project.doongdoong.domain.question.model.QuestionContent;
import com.project.doongdoong.module.IntegrationSupportTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.project.doongdoong.domain.question.model.QuestionContent.*;

class QuestionRepositoryTest extends IntegrationSupportTest {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    AnalysisRepository analysisRepository;

    @DisplayName("해당하는 분석에 대한 모든 질문을 삭제한다.")
    @Test
    void deleteQuestionsById() {
        // given
        Analysis analysis = Analysis.builder()
                .build();

        Question question1 = createQuestion(UNFIXED_QUESTION1);
        Question question2 = createQuestion(UNFIXED_QUESTION3);
        Question question3 = createQuestion(FIXED_QUESTION1);
        Question question4 = createQuestion(UNFIXED_QUESTION4);

        question1.connectAnalysis(analysis);
        question2.connectAnalysis(analysis);
        question3.connectAnalysis(analysis);
        question4.connectAnalysis(analysis);

        analysisRepository.save(analysis);
        questionRepository.saveAll(List.of(question1, question2, question3, question4));

        Question question5 = createQuestion(FIXED_QUESTION4);
        questionRepository.save(question5);

        // when
        questionRepository.deleteQuestionsById(analysis.getId());

        // then
        List<Question> result = questionRepository.findAll();
        Assertions.assertThat(result).hasSize(1)
                .extracting("questionContent")
                .containsExactly(FIXED_QUESTION4);
    }

    private Question createQuestion(QuestionContent questionContent) {
        return Question.of(questionContent);
    }
}