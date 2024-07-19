package com.project.doongdoong.domain.question.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum QuestionContent {

    FIXED_QUESTION1("어떤 이유로 상담이 필요하신가요?", 1, true),
    FIXED_QUESTION2("당신의 감정에 무엇이 가장 큰 영향을 주었나요?",2, true),
    FIXED_QUESTION3("과거나 현재 어렵거나 힘들게 했던 문제가 있었다면 그 문제로 인해, 당신은 지금까지 어땠나요? 무엇을 느끼고 어떤 감정을 느꼈어요?", 3, true),
    FIXED_QUESTION4("더 만족스럽고 행복한 삶을 위해, 당신은 무엇을 바꿔보고 싶나요?", 4, true),
    FIXED_QUESTION5("문제를 해결하기 위해 이전에 시도해 본 것들이 있나요? " +
            "있다면 그중 가장 효과 있던 방법은 무엇기고 당신만의 방법으로 시도해본 것이 있나요?", 5, true),
    FIXED_QUESTION6("문제를 더 나아지게 만들 수 있게 당신이 지금 해볼 수 있는 것은 무엇인가요?", 6, true),
    UNFIXED_QUESTION1("울적/우울하다고 느낄때는 언제인가요?", 7, false),
    UNFIXED_QUESTION2("어떤 것을 하면 기분이 좋아지나요?", 8, false),
    UNFIXED_QUESTION3("당신만의 기분전환 방법이 있나요?", 9, false),
    UNFIXED_QUESTION4("기분이 처지고 힘들 때는 어떻게 벗어나곤 하나요?", 10, false);

    private final String text;
    private final int number;
    private final boolean isFixedQuestion;



    public static QuestionContent provideFixedQuestionContent(){
        Random random = new Random();
        List<QuestionContent> fixedQuestionContents = getFixedQuestionContents();

        return fixedQuestionContents.get(random.nextInt(fixedQuestionContents.size()));
    }

    public static List<QuestionContent> getFixedQuestionContents() {
        List<QuestionContent> fixedQuestionContents = Arrays.stream(values())
                .filter(question -> question.isFixedQuestion)
                .collect(Collectors.toList());
        return fixedQuestionContents;
    }

    public static QuestionContent provideUnFixedQuestionContent(){
        Random random = new Random();
        List<QuestionContent> unFixedQuestionContents = getUnFixedQuestionContents();

        return unFixedQuestionContents.get(random.nextInt(unFixedQuestionContents.size()));
    }

    public static List<QuestionContent> getUnFixedQuestionContents() {
        List<QuestionContent> unFixedQuestionContents = Arrays.stream(values())
                .filter(question -> !question.isFixedQuestion)
                .collect(Collectors.toList());
        return unFixedQuestionContents;
    }
    /*public static List<QuestionContent> provideQuestions(){

        List<QuestionContent> fixedQuestions = selectFixedQuestions();
        List<QuestionContent> unfixedQuestions = selectUnFixedQuestions();
        List<QuestionContent> allQuestions = combineLists(fixedQuestions, unfixedQuestions);

        return allQuestions;
    }
    private static List<QuestionContent> combineLists(List<QuestionContent> list1, List<QuestionContent> list2) {
        List<QuestionContent> combinedList = new ArrayList<>();
        combinedList.addAll(list1);
        combinedList.addAll(list2);
        return combinedList;
    }

    private static List<QuestionContent> selectUnFixedQuestions() {
        List<QuestionContent> unFixedQuestions = Arrays.stream(values())
                .filter(questionGenerator -> !questionGenerator.isFixedQuestion)
                .collect(Collectors.toList());
        Random random = new Random();

        while (unFixedQuestions.size() > QUESTION_SIZE) {
            unFixedQuestions.remove(random.nextInt(unFixedQuestions.size()));
        }
        return unFixedQuestions;
    }

    private static List<QuestionContent> selectFixedQuestions() {
        List<QuestionContent> fixedQuestions = Arrays.stream(values())
                .filter(questionGenerator -> questionGenerator.isFixedQuestion)
                .collect(Collectors.toList());
        Random random = new Random();

        while (fixedQuestions.size() > QUESTION_SIZE) {
            fixedQuestions.remove(random.nextInt(fixedQuestions.size()));
        }
        return fixedQuestions;
    }
*/
}
