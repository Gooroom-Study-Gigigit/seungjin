package com.project.doongdoong.domain.question.model;

import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.answer.model.Answer;
import com.project.doongdoong.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "question_id")
    private Long id;


    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private QuestionContent questionContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;

    @OneToOne(fetch = LAZY, cascade = CascadeType.REMOVE,orphanRemoval = true)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Builder
    public Question(QuestionContent questionContent) {
        this.questionContent = questionContent;
    }

    public void connectAnalysis(Analysis analysis){
        this.analysis = analysis;
        //analysis.getQuestions().add(this); // 무한 참조(heap space)로 인해 주석 처리. 원인을 못 찾음...
    }

    public void connectAnswer(Answer answer){
        this.answer = answer;
    }







}
