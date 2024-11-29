package com.project.doongdoong.domain.counsel.service;

import com.project.doongdoong.domain.analysis.exception.AllAnswersNotFoundException;
import com.project.doongdoong.domain.analysis.exception.AnalysisAccessDeny;
import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.analysis.repository.AnalysisRepository;
import com.project.doongdoong.domain.answer.model.Answer;
import com.project.doongdoong.domain.counsel.dto.request.CounselCreateRequest;
import com.project.doongdoong.domain.counsel.dto.response.CounselDetailResponse;
import com.project.doongdoong.domain.counsel.dto.response.CounselListResponse;
import com.project.doongdoong.domain.counsel.dto.response.CounselResponse;
import com.project.doongdoong.domain.counsel.dto.response.CounselResultResponse;
import com.project.doongdoong.domain.counsel.exception.CounselAlreadyProcessedException;
import com.project.doongdoong.domain.counsel.exception.CounselNotExistPageException;
import com.project.doongdoong.domain.counsel.exception.CounselNotFoundException;
import com.project.doongdoong.domain.counsel.exception.UnAuthorizedForCounselException;
import com.project.doongdoong.domain.counsel.model.Counsel;
import com.project.doongdoong.domain.counsel.model.CounselType;
import com.project.doongdoong.domain.counsel.repository.CounselRepository;
import com.project.doongdoong.domain.user.exeception.UserNotFoundException;
import com.project.doongdoong.domain.user.model.SocialType;
import com.project.doongdoong.domain.user.model.User;
import com.project.doongdoong.domain.user.repository.UserRepository;
import com.project.doongdoong.global.dto.response.CounselAiResponse;
import com.project.doongdoong.global.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CounselServiceImpl implements CounselService {

    private final AnalysisRepository analysisRepository;
    private final CounselRepository counselRepository;
    private final UserRepository userRepository;
    private final WebClientUtil webClientUtil;
    private final static int COUNSEL_PAGE_SIZE = 10;


    @Transactional
    @Override
    public CounselResultResponse consult(String uniqueValue, CounselCreateRequest request) {
        String[] values = parseUniqueValue(uniqueValue); // 사용자 정보 찾기
        User user = userRepository.findBySocialTypeAndSocialId(SocialType.customValueOf(values[1]), values[0])
                .orElseThrow(() -> new UserNotFoundException());

        Counsel counsel = Counsel.builder() // 상담 객체 생성
                .question(request.getQuestion())
                .counselType(CounselType.from(request.getCounselType()))
                .user(user)
                .build();
        /**
         * 1. 고민은 무조건
         * 2. analysisId 여부
         * 3. 각 분석 답변 4개 -> 하나의 스트링 -> AI한테 (imformation)
         */
        if (request.getAnalysisId() != null) { // 기존 분석 결과 반영하기
            Analysis findAnalysis = analysisRepository.findByUserAndId(user, request.getAnalysisId()).orElseThrow(() -> new AnalysisAccessDeny());
            checkCounselAlreadyProcessed(findAnalysis); // 해당 분석의 정보로 상담한 경우 예외
            counsel.addAnalysis(findAnalysis); // 연관관계 매핑
        }

        HashMap<String, Object> parameters = setupParameters(counsel);

        CounselAiResponse counselAiResponse = webClientUtil.callConsult(parameters);

        counsel.saveAnswer(counselAiResponse.getAnswer());
        counsel.saveImageUrl(counselAiResponse.getImageUrl());
        Counsel savedCounsel = counselRepository.save(counsel);

        return CounselResultResponse.builder()
                .counselId(savedCounsel.getId())
                .counselContent(counselAiResponse.getAnswer())
                .imageUrl(counselAiResponse.getImageUrl())
                .build();

    }

    private HashMap<String, Object> setupParameters(Counsel counsel) {
        HashMap<String, Object> parameters = new HashMap<String, Object>(); // 외부 API request 설정
        parameters.put("question", counsel.getQuestion()); // 고민은 필수
        parameters.put("category", counsel.getCounselType().getContent());


        Optional.ofNullable(counsel.getAnalysis()) // 분석 -> 상담 으로 연결되는 경우, 분석에 대한 답변 항목 추가
                .ifPresent(analysis -> {
                    if (analysis.getAnswers().size() != 4) {
                        throw new AllAnswersNotFoundException();
                    }
                    String content = "";
                    for (Answer answer : analysis.getAnswers()) {
                        content += answer.getContent() + " ";
                    }
                    parameters.put("analysisContent", content);
                });
        if (parameters.get("analysisContent") == null) { // 없는 경우, 빈 문자열값
            parameters.put("analysisContent", "");
        }
        return parameters;
    }

    private void checkCounselAlreadyProcessed(Analysis findAnalysis) {
        if (findAnalysis.getCounsel() != null) {
            throw new CounselAlreadyProcessedException();
        }
    }

    @Override
    public CounselDetailResponse findCouselContent(String socialId, Long counselId) {
        String[] value = parseUniqueValue(socialId);
        log.info(value[0], value[1]);
        User findUser = userRepository.findBySocialTypeAndSocialId(SocialType.customValueOf(value[1]), value[0])
                .orElseThrow(() -> new UserNotFoundException());
        Counsel findCounsel = counselRepository.findWithAnalysisById(counselId).orElseThrow(() -> new CounselNotFoundException());

        if (!findCounsel.getUser().getId().equals(findUser.getId())) { // 사용자 본인의 상담만 확인 가능
            throw new UnAuthorizedForCounselException();
        }

        return CounselDetailResponse.builder()
                .data(findCounsel.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .counselId(findCounsel.getId())
                .question(findCounsel.getQuestion())
                .answer(findCounsel.getAnswer())
                .imageUrl(findCounsel.getImageUrl())
                .counselType(findCounsel.getCounselType().getContent())
                .build();
    }

    @Override
    public CounselListResponse findConusels(String uniqueValue, int pageNumber) {
        String[] value = parseUniqueValue(uniqueValue);
        User findUser = userRepository.findBySocialTypeAndSocialId(SocialType.customValueOf(value[1]), value[0])
                .orElseThrow(() -> new UserNotFoundException());

        pageNumber -= 1;
        PageRequest pageRequest = PageRequest.of(pageNumber, COUNSEL_PAGE_SIZE);
        Page<Counsel> counselsPage = counselRepository.searchPageCounselList(findUser, pageRequest);

        if (pageNumber + 1 > counselsPage.getTotalPages()) { // 존재하지 않는 페이지에 접근하는 경우
            throw new CounselNotExistPageException();
        }

        CounselListResponse response = CounselListResponse.builder()
                .currentPage(counselsPage.getNumber() + 1)
                .numberPerPage(counselsPage.getSize())
                .totalPage(counselsPage.getTotalPages())
                .totalElements(counselsPage.getTotalElements())
                .counselContent(counselsPage.getContent().stream()
                        .map(counsel ->
                                CounselResponse.builder()
                                        .date(counsel.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                                        .counselId(counsel.getId())
                                        .isAnalysisUsed(counsel.hasAnaylsis())
                                        .counselType(counsel.getCounselType().getContent())
                                        .build()
                        )
                        .collect(Collectors.toList())
                )
                .build();

        return response;
    }

    private static String[] parseUniqueValue(String uniqueValue) {
        String[] values = uniqueValue.split("_"); // 사용자 찾기
        return values;
    }
}
