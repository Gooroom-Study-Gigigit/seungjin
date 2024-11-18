package com.project.doongdoong.domain.answer.repository;

import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.analysis.repository.AnalysisRepository;
import com.project.doongdoong.domain.answer.model.Answer;
import com.project.doongdoong.module.IntegrationSupportTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerRepositoryTest extends IntegrationSupportTest {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    AnalysisRepository analysisRepository;

    @DisplayName("해당하는 분석에 대한 모든 답변을 삭제한다.")
    @Test
    void deleteAnswersById() {
        // given
        Analysis analysis = Analysis.builder()
                .build();

        Answer answer1 = createAnswer("답변 1입니다.");
        Answer answer2 = createAnswer("답변 2입니다.");
        Answer answer3 = createAnswer("답변 3입니다.");
        Answer answer4 = createAnswer("답변 4입니다.");

        answer1.connectAnalysis(analysis);
        answer2.connectAnalysis(analysis);
        answer3.connectAnalysis(analysis);
        answer4.connectAnalysis(analysis);

        analysisRepository.save(analysis);

        Answer answer5 = createAnswer("답변 5입니다.");
        answerRepository.save(answer5);

        // when
        answerRepository.deleteAnswersById(analysis.getId());

        // then
        List<Answer> result = answerRepository.findAll();
        Assertions.assertThat(result).hasSize(1)
                .extracting("content")
                .containsExactly("답변 5입니다.");
    }

    private Answer createAnswer(String content) {
        return Answer.builder()
                .content(content)
                .build();
    }

}