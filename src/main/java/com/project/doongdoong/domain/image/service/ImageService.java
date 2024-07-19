package com.project.doongdoong.domain.image.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.project.doongdoong.domain.image.dto.request.ImageSaveRequestDto;
import com.project.doongdoong.domain.image.dto.response.ImageDetailResponseDto;
import com.project.doongdoong.domain.image.dto.response.ImagesResponseDto;
import com.project.doongdoong.domain.image.exception.FileDeleteException;
import com.project.doongdoong.domain.image.exception.FileEmptyException;
import com.project.doongdoong.domain.image.exception.FileUploadException;
import com.project.doongdoong.domain.image.exception.ImageUrlNotFoundException;
import com.project.doongdoong.domain.image.model.Image;
import com.project.doongdoong.domain.image.repository.ImageRepository;
import com.project.doongdoong.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService {

    private final String KEY = "image/";

    @Value("${cloud.aws.bucket}")
    private String bucketName;
    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;

    @Transactional
    public ImagesResponseDto saveImages(ImageSaveRequestDto saveDto) {
        ImagesResponseDto resultList = new ImagesResponseDto();
        for(MultipartFile multipartFile : saveDto.getImages()) {
            if(multipartFile.isEmpty()){
                new FileEmptyException();
            }
            ImageDetailResponseDto detailResponseDto = saveImage(multipartFile);
            resultList.getImagesResponse().add(detailResponseDto);
        }
        return resultList;
    }

    public ImageDetailResponseDto saveImage(MultipartFile multipartFile) {
        String originalName = multipartFile.getOriginalFilename();
        Image image = new Image(originalName);
        String filename = KEY + image.getStoredName();


        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());
            amazonS3Client.putObject(bucketName, filename, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();
            image.changeAccessUrl(accessUrl);
        } catch(SdkClientException | IOException e) {
            log.error("파일 업로드 과정 실패  {}", e.getMessage());
            new FileUploadException();
        }

        imageRepository.save(image);

        return ImageDetailResponseDto.of(image.getAccessUrl());
    }

    public void deleteImage(String imageUrl) {
        Image image = imageRepository.findByAccessUrl(imageUrl).
                orElseThrow(() -> new ImageUrlNotFoundException());
        try{
            imageRepository.delete(image);
            amazonS3Client.deleteObject(bucketName, image.getStoredName());
        }
        catch(SdkClientException e) {
            log.error("이미지 삭제 오류 발생 -> {}", e.getMessage());
            new FileDeleteException();
        }
    }

    @Transactional
    public void deleteImages(List<String> imageUrls) {
        for (String imageUrl : imageUrls) {
            deleteImage(imageUrl);
        }

    }


}
