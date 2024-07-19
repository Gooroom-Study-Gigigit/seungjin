package com.project.doongdoong.domain.image.repository;

import com.project.doongdoong.domain.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByAccessUrl(String accessUrl);
}
