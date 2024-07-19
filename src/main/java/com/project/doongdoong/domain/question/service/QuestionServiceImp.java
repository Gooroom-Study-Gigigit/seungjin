package com.project.doongdoong.domain.question.service;

import com.project.doongdoong.domain.question.model.Question;
import com.project.doongdoong.domain.question.model.QuestionContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionServiceImp implements QuestionService {

    private static final int FIXED_QUESTION_SIZE = 2;
    private static final int UNFIXED_QUESTION_SIZE = 2;



    @Override
    public Question createFixedQuestion() {
        QuestionContent questionContent = QuestionContent.provideFixedQuestionContent();
        Question question = Question.builder()
                .questionContent(questionContent)
                .build();

        return question;

    }

    @Override
    public Question createUnFixedQuestion() {
        QuestionContent questionContent = QuestionContent.provideUnFixedQuestionContent();
        Question question = Question.builder()
                .questionContent(questionContent)
                .build();

        return question;

    }

    @Transactional
    @Override
    public List<Question> createQuestions() {
        Set<Question> fixedQuestionList = getFixedQuestionList(FIXED_QUESTION_SIZE);
        Set<Question> unFixedQuestionList = getUnFixedQuestionList(UNFIXED_QUESTION_SIZE);

        List<Question> mergedQuestionList = new ArrayList<>(fixedQuestionList);
        mergedQuestionList.addAll(unFixedQuestionList);

        return mergedQuestionList;
    }

    private Set<Question> getFixedQuestionList(int size) {
        Set<Question> questions = new HashSet<>();
        while(questions.size() < FIXED_QUESTION_SIZE){
            Question fixedQuestion = createFixedQuestion();
            addIfNotDuplicateQuestionContent(questions, fixedQuestion);
        }
        return questions;
    }

    private Set<Question> getUnFixedQuestionList(int size) {
        Set<Question> questions = new HashSet<>();
        while(questions.size() < UNFIXED_QUESTION_SIZE){
            Question unFixedQuestion = createUnFixedQuestion();
            addIfNotDuplicateQuestionContent(questions, unFixedQuestion);
        }
        return questions;
    }

    private void addIfNotDuplicateQuestionContent(Set<Question> questions, Question now) {
        List<QuestionContent> questionContents = questions.stream()
                .map(question -> question.getQuestionContent())
                .collect(Collectors.toList());
        if(!questionContents.contains(now.getQuestionContent())) {
            questions.add(now);
        }
    }

}
