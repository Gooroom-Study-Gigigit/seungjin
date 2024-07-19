package com.project.doongdoong.domain.image.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ImagesResponseDto {
    private List<ImageDetailResponseDto> ImagesResponse = new ArrayList<>();
}
