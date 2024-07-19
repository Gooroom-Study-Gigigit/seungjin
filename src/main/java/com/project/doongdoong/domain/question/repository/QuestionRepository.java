package com.project.doongdoong.domain.question.repository;

import com.project.doongdoong.domain.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
