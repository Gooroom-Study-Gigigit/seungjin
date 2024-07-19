package com.project.doongdoong.domain.voice.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class VoiceSaveRequestDto {
    private List<MultipartFile> voices;

    @Builder
    public VoiceSaveRequestDto(List<MultipartFile> voices) {
        this.voices = voices;
    }

}
