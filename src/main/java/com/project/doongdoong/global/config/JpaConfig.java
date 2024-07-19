package com.project.doongdoong.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // Mock을 사용한 test를 사용하기 위해 따로 @EnableJpaAuditing를 적용한 빈으로 등록 
public class JpaConfig {
}
