package com.project.doongdoong.domain.analysis.service;

import com.project.doongdoong.domain.IntegrationSupportTest;
import com.project.doongdoong.domain.analysis.dto.response.AnalysisCreateResponseDto;
import com.project.doongdoong.domain.question.model.QuestionContent;
import com.project.doongdoong.domain.user.exeception.UserNotFoundException;
import com.project.doongdoong.domain.user.model.SocialType;
import com.project.doongdoong.domain.user.model.User;
import com.project.doongdoong.domain.user.repository.UserRepository;
import com.project.doongdoong.domain.voice.model.Voice;
import com.project.doongdoong.domain.voice.repository.VoiceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnalysisServiceImpTest extends IntegrationSupportTest {

    @Autowired
    AnalysisService analysisService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    VoiceRepository voiceRepository;

    @TestFactory
    @DisplayName("서비스 회원 정보와 존재하지 않는 서비스 회원 정보의 경우, 분석에 관한 정보 접근 시나리오")
    Collection<DynamicTest> createAnalysis(){
        //given
        User user1 = createUser("socialId", SocialType.APPLE);
        User savedUser = userRepository.save(user1);
        String uniqueValue1 = savedUser.getSocialId() + "_" + savedUser.getSocialType().getText();

        for(QuestionContent questionContent : QuestionContent.values()){
            Voice voice = Voice.initVoiceContentBuilder()
                    .originName(questionContent.getText() +"_voice.mp3")
                    .questionContent(questionContent)
                    .build();
            voice.changeAccessUrl("임의의 접근 url 주소");
            voiceRepository.save(voice);
        }

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

    private static User createUser(String socialId, SocialType socialType) {
        return User.builder()
                .socialId(socialId)
                .socialType(socialType)
                .build();
    }


}