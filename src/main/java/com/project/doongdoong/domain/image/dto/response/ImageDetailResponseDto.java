package com.project.doongdoong.domain.image.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageDetailResponseDto {

    //private Long imageId;
    private String accessUrl;

    @Builder
    public ImageDetailResponseDto(String accessUrl) {
        //this.imageId = imageId;
        this.accessUrl = accessUrl;
    }

    public static ImageDetailResponseDto of(String accessUrl){
        return ImageDetailResponseDto.builder()
                .accessUrl(accessUrl)
                .build();
    }


}
