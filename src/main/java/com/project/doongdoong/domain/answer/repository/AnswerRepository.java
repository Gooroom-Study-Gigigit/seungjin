package com.project.doongdoong.domain.answer.repository;

import com.project.doongdoong.domain.answer.model.Answer;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Answer answer where answer.analysis.id = :analysisId")
    void deleteAnswersById(@Param("analysisId") Long analysisId);

    @Modifying
    @Query("update Answer a set a.voice = null where a.analysis.id = :analysisId")
    void detachVoiceFromAnswersByAnalysisId(@Param("analysisId") Long analysisId);

}
