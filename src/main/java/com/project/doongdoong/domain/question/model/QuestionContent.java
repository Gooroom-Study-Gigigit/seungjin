package com.project.doongdoong.domain.question.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@Getter
@RequiredArgsConstructor
public enum QuestionContent {

    FIXED_QUESTION1("어떤 이유로 상담이 필요하신가요?", 1, true),
    FIXED_QUESTION2("당신의 감정에 무엇이 가장 큰 영향을 주었나요?", 2, true),
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

    private static final List<QuestionContent> FIXED_QUESTION_CONTENTS = List.of(
            FIXED_QUESTION1, FIXED_QUESTION2, FIXED_QUESTION3, FIXED_QUESTION4, FIXED_QUESTION5, FIXED_QUESTION6
    );
    private static final List<QuestionContent> UNFIXED_QUESTION_CONTENTS = List.of(
            UNFIXED_QUESTION1, UNFIXED_QUESTION2, UNFIXED_QUESTION3, UNFIXED_QUESTION4
    );
    private static final Random random = new Random();


    public static QuestionContent provideRandomFixedQuestionContent() {
        return FIXED_QUESTION_CONTENTS.get(randomIndex(FIXED_QUESTION_CONTENTS.size()));
    }

    private static int randomIndex(int size) {
        return random.nextInt(size);
    }


    public static QuestionContent provideRandomUnFixedQuestionContent() {

        return UNFIXED_QUESTION_CONTENTS.get(randomIndex(UNFIXED_QUESTION_CONTENTS.size()));
    }

}