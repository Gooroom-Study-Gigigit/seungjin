package com.project.doongdoong.domain.analysis.repository;

import com.project.doongdoong.domain.analysis.dto.response.FeelingStateResponseDto;
import com.project.doongdoong.domain.analysis.model.Analysis;
import com.project.doongdoong.domain.analysis.repository.querydls.AnalysisRepositoryCustom;
import com.project.doongdoong.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository // JPA가 @Repository 없이도 빈 등록해줌.
public interface AnalysisRepository extends JpaRepository<Analysis, Long>, AnalysisRepositoryCustom {


    @Query("select a from Analysis a left outer join fetch a.counsel where a.user = :user and a.id = :id")
    Optional<Analysis> findByUserAndId(@Param("user") User user, @Param("id") Long analysisId);

    Page<Analysis> findAllByUserOrderByCreatedTime(User user, Pageable pageable);

    // 현재 시간 기준으로 일주일 치 분석값 하루 기준으로 그룹핑해서 가져오기
    // between보다 <= >= 이게 속도 빠르다그랫음
    @Query("select new com.project.doongdoong.domain.analysis.dto.response.FeelingStateResponseDto" +
            "(CONCAT(YEAR(a.analyzeTime), '-', MONTH(a.analyzeTime), '-', DAY(a.analyzeTime)), avg(a.feelingState))" +
            "from Analysis a where a.user = :user " +
            "and a.analyzeTime between :startTime AND :endTime" +
            " group by YEAR(a.analyzeTime), MONTH(a.analyzeTime), DAY(a.analyzeTime) ")
    List<FeelingStateResponseDto> findAllByDateBetween(@Param("user") User user
            , @Param("startTime") LocalDate startTime, @Param("endTime") LocalDate endTime);

    Optional<Analysis> findFirstByUserOrderByAnalyzeTimeDesc(User user);

    @Query("select analysis from Analysis analysis join fetch analysis.questions where analysis.id = :analysisId")
    Optional<Analysis> findAnalysisWithQuestion(@Param("analysisId") Long analysisId);

    @Query("select analysis from Analysis analysis left outer join analysis.counsel where analysis.id = :id")
    Optional<Analysis> findAnalysis(Long id);
}
