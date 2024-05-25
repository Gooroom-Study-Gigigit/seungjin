package com.project.doongdoong.domain.consultation.model;

import com.project.doongdoong.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QAPair extends BaseEntity {

    @Id
    @Column(name = "qapair_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_id")
    private Counsel counsel;

    @Builder
    public QAPair(String question, String answer, Counsel counsel) {
        this.question = question;
        this.answer = answer;
        this.counsel = counsel;
    }
}
