package com.project.doongdoong.domain.question.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {
    private final List<Question> questions;

    private Questions(List<Question> questions) {
        this.questions = questions;
    }

    public static Questions of(List<Question> questions){
        return new Questions(questions);
    }


    public Questions extractRandomQuestions(int size) {
        ArrayList<Question> questionList = new ArrayList<>(questions);
        Collections.shuffle(questionList);
        List<Question> randomQuestions = questionList.subList(0, size);

        return of(randomQuestions);
    }

    public List<Question> sumQuestions(Questions unFixedQuestions) {
        ArrayList<Question> mergedQuestions = new ArrayList<>(questions);
        mergedQuestions.addAll(unFixedQuestions.questions);

        return mergedQuestions;
    }
}