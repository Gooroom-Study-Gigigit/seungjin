package com.project.doongdoong.domain.image.controller;

import com.project.doongdoong.domain.image.dto.request.ImageDeleteRequestDto;
import com.project.doongdoong.domain.image.dto.request.ImageSaveRequestDto;
import com.project.doongdoong.domain.image.dto.response.ImageDetailResponseDto;
import com.project.doongdoong.domain.image.dto.response.ImagesResponseDto;
import com.project.doongdoong.domain.image.service.ImageService;
import com.project.doongdoong.global.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController @Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "이미지 API", description = "이미지 등록, 삭제 API")
public class ImageController {
    private final ImageService imageService;

    @Operation(summary = "이미지 등록 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "이미지 등록 성공",
                    content = @Content(
                            examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"code\": 200,\n" +
                                            "    \"state\": \"OK\",\n" +
                                            "    \"message\": null,\n" +
                                            "    \"data\": {\n" +
                                            "        \"imagesResponse\": [\n" +
                                            "            {\n" +
                                            "                \"imageId\": 1,\n" +
                                            "                \"accessUrl\": \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/1a187841-9e84-47cd-8b38-8d33863b88c1.png\"\n" +
                                            "            },\n" +
                                            "            {\n" +
                                            "                \"imageId\": 2,\n" +
                                            "                \"accessUrl\": \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/abf163bf-5ad8-40d3-8b28-3c2980fab79f.png\"\n" +
                                            "            },\n" +
                                            "            {\n" +
                                            "                \"imageId\": 3,\n" +
                                            "                \"accessUrl\": \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/b122e0c3-91be-417d-8c1c-d2413b02e199.png\"\n" +
                                            "            }\n" +
                                            "        ]\n" +
                                            "    }\n" +
                                            "}")
                            }
                    )
            )
    })
    @Parameter(description = "Authorization token", required = true,schema = @Schema(type = "string"), in = ParameterIn.HEADER)
    @PostMapping("/image")
    public ApiResponse<ImagesResponseDto> saveImages(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = ImageSaveRequestDto.class)
                    )
            )
            @ModelAttribute @Valid ImageSaveRequestDto imageSaveDto) {
        return ApiResponse.of(HttpStatus.OK, null, imageService.saveImages(imageSaveDto));
    }

    /*@Operation(summary = "이미지 삭제 API")
    @ApiResponses(value = {

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "이미지 삭제 성공",
                    content = @Content(
                            examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"code\": 200,\n" +
                                            "    \"state\": \"OK\",\n" +
                                            "    \"message\": null,\n" +
                                            "    \"data\": \"delete success\"\n" +
                                            "}")
                            }
                    )
            )
    })
    @Parameter(description = "Authorization token", required = true,schema = @Schema(type = "string"), in = ParameterIn.HEADER)
    @DeleteMapping("/image")
    public ApiResponse<String> modifyImages(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ImageDeleteRequestDto.class),
                            examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"urls\": [\n" +
                                            "        \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/1a187841-9e84-47cd-8b38-8d33863b88c1.png\",\n" +
                                            "        \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/abf163bf-5ad8-40d3-8b28-3c2980fab79f.png\",\n" +
                                            "        \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/b122e0c3-91be-417d-8c1c-d2413b02e199.png\"\n" +
                                            "    ]\n" +
                                            "}")
                            }
                    )
            )
            @RequestBody @Valid ImageDeleteRequestDto imageDeleteDto) {
        imageService.deleteImages(imageDeleteDto.getUrls());
        return ApiResponse.of(HttpStatus.OK, null, "delete success");
    }*/

    @Operation(summary = "이미지 삭제 API")
    @ApiResponses(value = {
           
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "이미지 삭제 성공",
                    content = @Content(
                            examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"code\": 200,\n" +
                                            "    \"state\": \"OK\",\n" +
                                            "    \"message\": null,\n" +
                                            "    \"data\": \"delete success\"\n" +
                                            "}")
                            }
                    )
            )
    })
    @Parameter(description = "Authorization token", required = true,schema = @Schema(type = "string"), in = ParameterIn.HEADER)
    @DeleteMapping("/image")
    public ApiResponse<String> deleteImages(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ImageDeleteRequestDto.class),
                            examples = {
                                    @ExampleObject(value = "{\n" +
                                            "    \"urls\": [\n" +
                                            "        \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/1a187841-9e84-47cd-8b38-8d33863b88c1.png\",\n" +
                                            "        \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/abf163bf-5ad8-40d3-8b28-3c2980fab79f.png\",\n" +
                                            "        \"https://poda-s3-bucket.s3.ap-northeast-2.amazonaws.com/b122e0c3-91be-417d-8c1c-d2413b02e199.png\"\n" +
                                            "    ]\n" +
                                            "}")
                            }
                    )
            )
            @RequestBody @Valid ImageDeleteRequestDto imageDeleteDto) {
        imageService.deleteImages(imageDeleteDto.getUrls());
        return ApiResponse.of(HttpStatus.OK, null, "delete success");
    }
    /**
     * 현재 이미지는 심리 분석에 필요한 일기(글 정보)에 속하기 때문에 나중에 Diary에 속한 서비스 메소드로 진행될 것이다.
     * 위와 같이 controlle 단에서 직접적인 API 호출 X
     * 이미지는 url 자체로만 표현되므로 업데이트란 개념이 굳이 필요없다.
     *
     * 1. 이미지를 수정하려고 하면 삭제하고 재업로드하면 되므로 이미지 수정 API를 만들지 않음.
     * 2. 이미지 조회는 생성하자마자 접근 url을 줘서 client 측에서 url 정보를 가지고 있으므로 조회 API는 만들지 않음.
     */

}
