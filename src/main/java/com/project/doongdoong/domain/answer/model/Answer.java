package com.project.doongdoong.domain.answer.model;

import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.voice.model.Voice;
import com.project.doongdoong.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class Answer extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(length = 5000)
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;

    @OneToOne(fetch = LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "voice_id", unique = true)
    private Voice voice;

    @Builder
    public Answer(String content, Voice voice) {
        this.content = content;
        this.voice = voice;
    }

    public void connectAnalysis(Analysis analysis){
        if(this.analysis != null)
            return;
        this.analysis = analysis;
        analysis.getAnswers().add(this);
    }

    public void disconnectWithVoice(){
        this.voice = null;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
