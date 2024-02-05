package com.project.doongdoong.global.repositoty;

import com.project.doongdoong.global.BlackAccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackAccessTokenRepository extends CrudRepository<BlackAccessToken, String> {
    Optional<BlackAccessToken> findByOtherKey(String key);
    Optional<BlackAccessToken> findByAccessToken(String accessToken);
}
