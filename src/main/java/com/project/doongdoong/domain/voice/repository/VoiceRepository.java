package com.project.doongdoong.domain.voice.repository;

import com.project.doongdoong.domain.question.model.QuestionContent;
import com.project.doongdoong.domain.voice.model.Voice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoiceRepository extends JpaRepository<Voice, Long> {
    public Optional<Voice> findByAccessUrl(String voiceUrl);

    public Optional<Voice> findVoiceByQuestionContent(QuestionContent questionContent);
    public List<Voice> findVoiceAllByQuestionContentIn(List<QuestionContent> questionContent);

    public Optional<Voice> findVoiceByAccessUrl(String accessUrl);
}

