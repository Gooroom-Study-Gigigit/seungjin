package com.project.doongdoong.domain.question.service;

import com.project.doongdoong.domain.question.model.Question;
import com.project.doongdoong.domain.question.model.QuestionContent;
import com.project.doongdoong.domain.question.model.Questions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionProvider implements QuestionProvidable {

    private static final int FIXED_QUESTION_SIZE = 2;
    private static final int UNFIXED_QUESTION_SIZE = 2;


    @Override
    public Question createFixedQuestion() {
        QuestionContent fixedQuestionContent = QuestionContent.provideRandomFixedQuestionContent();

        return Question.of(fixedQuestionContent);

    }

    @Override
    public Question createUnFixedQuestion() {
        QuestionContent unQuestionContent = QuestionContent.provideRandomUnFixedQuestionContent();

        return Question.of(unQuestionContent);
    }


    @Override
    public List<Question> createRandomQuestions() {
        Questions fixedQuestions = getQuestions(QuestionContent.getFixedQuestionContents(), FIXED_QUESTION_SIZE);
        Questions unFixedQuestions = getQuestions(QuestionContent.getUnFixedQuestionContents(), UNFIXED_QUESTION_SIZE);

        return fixedQuestions.addQuestions(unFixedQuestions);
    }

    private Questions getQuestions(List<QuestionContent> questionContents, int size) {
        List<Question> questionList = getQuestionListFrom(questionContents);

        Questions questionsCandidate = Questions.of(questionList);

        return questionsCandidate.extractRandomQuestions(size);
    }

    private List<Question> getQuestionListFrom(List<QuestionContent> questionContents) {
        return questionContents.stream()
                .map(questionContent -> Question.of(questionContent))
                .collect(Collectors.toList());
    }


}