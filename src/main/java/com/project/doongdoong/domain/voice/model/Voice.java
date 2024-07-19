package com.project.doongdoong.domain.voice.model;

import com.project.doongdoong.domain.question.model.QuestionContent;
import com.project.doongdoong.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Voice extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voiceId;

    private String originName; // 음성 파일의 본래 이름

    private String storedName; // 음성 파일이 S3에 저장될때 사용되는 이름

    private String accessUrl; // S3 내부 음성 파일에 접근할 수 있는 URL

    @Enumerated(EnumType.STRING)
    private QuestionContent questionContent;


    @Builder(builderClassName = "CommonBuilder", builderMethodName = "commonBuilder")
    public Voice(String originName) {
        this.originName = originName;
        this.storedName = gainFileName(originName);
        this.accessUrl = "";
    }
    @Builder(builderClassName = "InitVoiceContentBuilder", builderMethodName = "initVoiceContentBuilder")
    public Voice(String originName, QuestionContent questionContent) {
        this.originName = originName;
        this.storedName = gainFileName() + originName;
        this.accessUrl = "";
        this.questionContent = questionContent;
    }
    public void changeAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    // 이미지 파일의 확장자를 추출하는 메소드
    public String extractExtension(String originName) {
        int index = originName.lastIndexOf('.');

        return originName.substring(index, originName.length());
    }

    public String gainFileName(String originName) {
        return UUID.randomUUID()  + extractExtension(originName);
    }
    public String gainFileName() {
        return UUID.randomUUID().toString();
    }
}
