package com.project.doongdoong.domain.answer.service;

import com.project.doongdoong.domain.analysis.exception.AnalysisNotFoundException;
import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.analysis.repository.AnalysisRepository;
import com.project.doongdoong.domain.answer.dto.AnswerCreateResponseDto;
import com.project.doongdoong.domain.answer.exception.AnswerConflictException;
import com.project.doongdoong.domain.answer.model.Answer;
import com.project.doongdoong.domain.answer.repository.AnswerRepository;
import com.project.doongdoong.domain.question.exception.NoMatchingQuestionException;
import com.project.doongdoong.domain.question.model.Question;
import com.project.doongdoong.domain.voice.dto.response.VoiceDetailResponseDto;
import com.project.doongdoong.domain.voice.exception.VoiceUrlNotFoundException;
import com.project.doongdoong.domain.voice.model.Voice;
import com.project.doongdoong.domain.voice.repository.VoiceRepository;
import com.project.doongdoong.domain.voice.service.VoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImp implements AnswerService{

    private final VoiceRepository voiceRepository;
    private final VoiceService voiceService;
    private final AnswerRepository answerRepository;
    private final AnalysisRepository analysisRepository;

    public final static int MAX_ANSWER_COUNT = 4;


    @Transactional
    @Override
    public AnswerCreateResponseDto createAnswer(Long analysisId, MultipartFile file, Long questionId) {
        Analysis findAnalysis = analysisRepository.findAnalysisWithQuestion(analysisId)
                .orElseThrow(AnalysisNotFoundException::new);

        Question matchedQuestion = findAnalysis.getQuestions().stream()
                .filter(question -> (long)question.getId() == (long)questionId)
                .findFirst().orElseThrow(NoMatchingQuestionException::new);

        if(Optional.ofNullable(matchedQuestion.getAnswer()).isPresent()){ // 이미 설정된 question - answer이 존재할 때 다시 접근하려고 하면 예외 발생
            throw new AnswerConflictException();
        }

        VoiceDetailResponseDto voiceDto = voiceService.saveVoice(file);
        Voice voice = voiceRepository.findVoiceByAccessUrl(voiceDto.getAccessUrl()).orElseThrow(() -> new VoiceUrlNotFoundException());

        Answer answer = Answer.builder()
                .content(null)
                .voice(voice)
                .build();

        answer.connectAnalysis(findAnalysis);
        matchedQuestion.connectAnswer(answer);
        answerRepository.save(answer);

        return AnswerCreateResponseDto.builder()
                .answerId(answer.getId())
                .build();
    }
}
