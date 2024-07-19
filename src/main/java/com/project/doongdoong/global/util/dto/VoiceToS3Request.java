package com.project.doongdoong.global.util.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class VoiceToS3Request {
    private String fileKey;

    @Builder
    public VoiceToS3Request(String fileKey) {
        this.fileKey = fileKey;
    }
}
