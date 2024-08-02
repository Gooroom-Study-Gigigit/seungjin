package com.project.doongdoong.domain.analysis.service;

import com.project.doongdoong.domain.IntegrationSupportTest;
import com.project.doongdoong.domain.analysis.dto.response.AnalysisCreateResponseDto;
import com.project.doongdoong.domain.analysis.dto.response.AnalysisDetailResponse;
import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.analysis.repository.AnalysisRepository;
import com.project.doongdoong.domain.answer.model.Answer;
import com.project.doongdoong.domain.answer.repository.AnswerRepository;
import com.project.doongdoong.domain.question.model.Question;
import com.project.doongdoong.domain.question.model.QuestionContent;
import com.project.doongdoong.domain.user.exeception.UserNotFoundException;
import com.project.doongdoong.domain.user.model.SocialType;
import com.project.doongdoong.domain.user.model.User;
import com.project.doongdoong.domain.user.repository.UserRepository;
import com.project.doongdoong.domain.voice.model.Voice;
import com.project.doongdoong.domain.voice.repository.VoiceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class AnalysisServiceImpTest extends IntegrationSupportTest {

    // TODO: 2024-08-01 : 전체 완료까지 총 6개 테스트 작성 필요.
    // TODO: 2024-08-01 : 완료 메서드 : createAnalysis

    @Autowired AnalysisService analysisService;

    @Autowired UserRepository userRepository;
    @Autowired VoiceRepository voiceRepository;
    @Autowired AnswerRepository answerRepository;
    @Autowired AnalysisRepository analysisRepository;

    @TestFactory
    @DisplayName("서비스 회원 정보와 존재하지 않는 서비스 회원 정보의 경우, 분석에 관한 정보 접근 시나리오")
    Collection<DynamicTest> createAnalysis(){
        //given
        for(QuestionContent questionContent : QuestionContent.values()){
            Voice voice = Voice.initVoiceContentBuilder()
                    .originName(questionContent.getText() +"_voice.mp3")
                    .questionContent(questionContent)
                    .build();
            voice.changeAccessUrl("임의의 접근 url 주소");
            voiceRepository.save(voice);
        }

        User user1 = createUser("socialId", SocialType.APPLE);
        User savedUser = userRepository.save(user1);
        String uniqueValue1 = savedUser.getSocialId() + "_" + savedUser.getSocialType().getText();

        int analysisRelatedSize = 4;
        List<String> allQuestionTexts = Arrays.stream(QuestionContent.values())
                .map(QuestionContent::getText)
                .collect(Collectors.toList());

        //when
        return List.of(
                DynamicTest.dynamicTest("질문 목록에 대한 접근 url, 내용 등 분석에 사용할 정보를 생성할 수 있다.", () -> {
                    //when
                    AnalysisCreateResponseDto responseDto = analysisService.createAnalysis(uniqueValue1);
                    //then
                    assertThat(responseDto).isNotNull();
                    assertThat(responseDto.getAnalysisId()).isExactlyInstanceOf(Long.class);
                    assertThat(responseDto.getQuestionTexts())
                            .hasSize(analysisRelatedSize)
                            .allMatch(allQuestionTexts::contains);
                    assertThat(responseDto.getAccessUrls()).hasSize(analysisRelatedSize);

                }),
                DynamicTest.dynamicTest("존재하지 않는 사용자 정보로는 분석 관련 정보에 접근할 수 없습니다.", () -> {
                    //when
                    User user2 = createUser("notFoundSocialId", SocialType.GOOGLE);
                    String uniqueValue2 = user2.getSocialId() + "_" + user2.getSocialType();
                    //when & then
                    assertThatThrownBy(()->analysisService.createAnalysis(uniqueValue2))
                            .isInstanceOf(UserNotFoundException.class)
                            .hasMessage("해당 사용자는 존재하지 않습니다.");

                })
        );
    }

    @Test
    @DisplayName("분석 정보를 조회합니다.")
    void getAnalysis(){
        //given
        for(QuestionContent questionContent : QuestionContent.values()){ // initProvider 대체 -> TEST용
            Voice voice = Voice.initVoiceContentBuilder()
                    .originName(questionContent.getText() +"_voice.mp3")
                    .questionContent(questionContent)
                    .build();
            voice.changeAccessUrl("임의의 접근 url 주소");
            voiceRepository.save(voice);
        }

        Answer answer1 = createAnswer("답변1보이스를 STT로 변경한 텍스트");
        Answer answer2 = createAnswer("답변2보이스를 STT로 변경한 텍스트");
        Answer answer3 = createAnswer("답변3보이스를 STT로 변경한 텍스트");
        Answer answer4 = createAnswer("답변4보이스를 STT로 변경한 텍스트");
        List<Answer> answers = List.of(answer1, answer2, answer3, answer4);
        answerRepository.saveAll(answers);

        User user = createUser("socialId", SocialType.APPLE);
        Question question1 = createQuestion(QuestionContent.FIXED_QUESTION1);
        Question question2 = createQuestion(QuestionContent.FIXED_QUESTION2);
        Question question3 = createQuestion(QuestionContent.UNFIXED_QUESTION1);
        Question question4 = createQuestion(QuestionContent.UNFIXED_QUESTION3);
        List<Question> questions = List.of(question1, question2, question3, question4);
        Analysis analysis = createAnalysis(user, questions);

        question1.connectAnswer(answer1);
        question2.connectAnswer(answer2);
        question3.connectAnswer(answer3);
        question4.connectAnswer(answer4);

        userRepository.save(user);
        Analysis savedAnalysis = analysisRepository.save(analysis);

        LocalDate analyzeTime = LocalDate.now();
        double feelingState = 72.1;
        analysis.changeFeelingStateAndAnalyzeTime(feelingState, analyzeTime);

        int questionSize = 4;
        int answerSize = 4;
        //when
        AnalysisDetailResponse response = analysisService.getAnalysis(savedAnalysis.getId());
        //then
        assertThat(response.getQuestionContentVoiceUrls()).hasSize(questionSize);
        assertThat(response.getQuestionIds()).hasSize(answerSize);
        assertThat(response)
                .extracting(
                        "analysisId",
                        "feelingState",
                        "questionContent",
                        "answerContent"
                )
                .containsExactly(
                        analysis.getId(),
                        feelingState,
                        questions.stream()
                                .map(question -> question.getQuestionContent().getText())
                                .collect(Collectors.toList()),
                        answers.stream()
                                .map(answer -> answer.getContent())
                                .collect(Collectors.toList())
                );
    }


    private static Answer createAnswer(String content) {
        return Answer.builder()
                .content(content)
                .build();
    }

    private static Question createQuestion(QuestionContent questionContent) {
        Question question = Question.builder()
                .questionContent(questionContent)
                .build();

        return question;
    }

    private static Analysis createAnalysis(User user, List<Question> questions) {
        return Analysis.builder()
                .user(user)
                .questions(questions)
                .build();
    }

    private static User createUser(String socialId, SocialType socialType) {
        return User.builder()
                .socialId(socialId)
                .socialType(socialType)
                .build();
    }


}