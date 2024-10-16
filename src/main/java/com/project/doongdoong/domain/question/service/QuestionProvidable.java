package com.project.doongdoong.domain.question.service;

import com.project.doongdoong.domain.question.model.Question;

import java.util.List;

public interface QuestionProvidable {

    Question createFixedQuestion();
    Question createUnFixedQuestion();

    List<Question> createRandomQuestions();
}
