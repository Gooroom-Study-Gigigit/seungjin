package com.project.doongdoong.domain.question.service;

import com.project.doongdoong.domain.question.model.Question;
import com.project.doongdoong.domain.question.model.QuestionContent;

import java.util.List;

public interface QuestionService {

    Question createFixedQuestion();
    Question createUnFixedQuestion();

    List<Question> createQuestions();
}
