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
                "/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/actuator/**"
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
                    )/*.oauth2Login( oauth2 -> oauth2
                            .successHandler(successHandler())
                            .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                    .userService(oAuth2UserService))

                    )*/ // native app 형식이라 프론트(ios)에서 sdk로 모든 소셜 과정 처리 -> oauth2 clinet 대신 security가 제공하는 기본 값 사용하자
                    .addFilterBefore(new JwtAuthFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                    .build();
                     // 추후 웹 사용으로 인해 위 설정이 필요하다면 블로그 링크를 통해 다시 구현
                    //웹과 다르게 앱에서는 카카오 SDK와 같이 클라이언트 측에서 직접 OAuth 검증을 하므로 필요가 없음.
                    // OAuth2 로그인 설정
        }
        /*@Bean
        public AuthenticationSuccessHandler successHandler() {
            return ((request, response, authentication) -> {
                DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();

                String id = defaultOAuth2User.getAttributes().get("id").toString();
                String body = """
                    {"id":"%s"}
                    """.formatted(id);

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());

                PrintWriter writer = response.getWriter();
                writer.println(body);
                writer.flush();
            });
        }*/


    }
