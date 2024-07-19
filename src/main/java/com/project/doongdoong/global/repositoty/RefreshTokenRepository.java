package com.project.doongdoong.global.repositoty;

import com.project.doongdoong.domain.user.model.SocialType;
import com.project.doongdoong.global.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

    // accessToken으로 RefreshToken을 찾아온다.
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    Optional<RefreshToken> findByUniqueId(String uniqueId);
}