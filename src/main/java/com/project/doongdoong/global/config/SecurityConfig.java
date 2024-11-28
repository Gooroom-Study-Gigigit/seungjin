    package com.project.doongdoong.global.config;

    import com.project.doongdoong.global.fliter.JwtAuthFilter;
    import com.project.doongdoong.global.util.JwtProvider;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration @Slf4j
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final JwtProvider jwtProvider;
        public final static String[] ALLOW_REQUEST = {
                "/", "/api/v1/user/ping", "/api/v1/user/login-oauth", "/api/v1/user/reissue",
                "/api-docs/**", "/actuator/**", "/docs/index.html"
        };
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            return  http
                    .httpBasic(httpBasic -> httpBasic.disable())
                    .csrf(csrf -> csrf.disable())
                    // 모바일(프론트엔드)와 협업이 필요하므로 추후 cors 활성화 추가 필요
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) /**
                     세션관리 정책에 대한 설정을 STATELESS로 설정하는 코드입니다. jwt를 사용할거니 session은 빼기(다만 STATELESS는 세션이 있다면 사용함)
                     SessionCreationPolicy는 네가지 값이 존재합니다. 주의해야 할 점은 NEVER는 세션을 아예 사용하지 않겠다는 뜻이 아니라는 점에 유의해야 합니다
                     */
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(ALLOW_REQUEST).permitAll()
                                    .anyRequest().authenticated()
                    ).addFilterBefore(new JwtAuthFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                    .build();
        }


    }
