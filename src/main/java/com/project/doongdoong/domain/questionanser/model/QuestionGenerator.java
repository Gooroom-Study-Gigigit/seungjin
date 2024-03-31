package com.project.doongdoong.domain.questionanser.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum QuestionGenerator {

    FIXED_QUESTION1("오늘 느낀 감정에 대해 말해줘.", 1, true),
    FIXED_QUESTION2("행복한 사람인 거 같아? 이유도 함께 말해줘.",2, true),
    FIXED_QUESTION3("너는 어떤 일을 하고 싶어? 인생의 최종 목표가 뭐야?", 3, true),
    FIXED_QUESTION4("인생에서 제일 필요한 건 뭐라고 생각해?", 4, true),

    UNFIXED_QUESTION1("유연한 질문1", 5, false),
    UNFIXED_QUESTION2("유연한 질문2", 6, false),
    UNFIXED_QUESTION3("유연한 질문3", 7, false),
    UNFIXED_QUESTION4("유연한 질문4", 7, false);

    private final String text;
    private final int number;
    private final boolean isFixedQuestion;
    private static final int QUESTION_SIZE = 2;

    public static List<String> provideQuestions(){

        List<String> fixedQuestions = selectFixedQuestions();
        List<String> unfixedQuestions = selectUnFixedQuestions();
        List<String> allQuestions = combineLists(fixedQuestions, unfixedQuestions);

        return allQuestions;
    }
    private static List<String> combineLists(List<String> list1, List<String> list2) {
        List<String> combinedList = new ArrayList<>();
        combinedList.addAll(list1);
        combinedList.addAll(list2);
        return combinedList;
    }

    private static List<String> selectUnFixedQuestions() {
        List<String> unFixedQuestions = Arrays.stream(values())
                .filter(questionGenerator -> !questionGenerator.isFixedQuestion)
                .map(questionGenerator -> questionGenerator.getText())
                .collect(Collectors.toList());
        Random random = new Random();

        while (unFixedQuestions.size() > QUESTION_SIZE) {
            unFixedQuestions.remove(random.nextInt(unFixedQuestions.size()));
        }
        return unFixedQuestions;
    }

    private static List<String> selectFixedQuestions() {
        List<String> fixedQuestions = Arrays.stream(values())
                .filter(questionGenerator -> questionGenerator.isFixedQuestion)
                .map(questionGenerator -> questionGenerator.getText())
                .collect(Collectors.toList());
        Random random = new Random();

        while (fixedQuestions.size() > QUESTION_SIZE) {
            fixedQuestions.remove(random.nextInt(fixedQuestions.size()));
        }
        return fixedQuestions;
    }

}
