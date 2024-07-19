package com.project.doongdoong.domain.counsel.repository.querydsl;

import com.project.doongdoong.domain.counsel.model.Counsel;
import com.project.doongdoong.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CounselCustomRepository {

    Page<Counsel> searchPageCounselList(User user, Pageable pageable);
}
