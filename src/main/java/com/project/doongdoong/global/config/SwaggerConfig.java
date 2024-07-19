package com.project.doongdoong.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1") // 그룹명 지정
                .pathsToMatch("/api/v1/**") // pathsToMatch로 원하는 경로의 api만 나오도록 설정
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        Info info = new Info()
                .title("Example API 문서") // 타이틀
                .description("잘못된 부분이나 오류 발생 시 바로 말씀해주세요.") // 문서 설명
                .contact(new Contact() // 연락처
                        .name("김승진")
                        .email("whffkaos007@naver.com"));

        // Security 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
        // Security 요청 설정
        SecurityRequirement addSecurityItem = new SecurityRequirement();
        addSecurityItem.addList("JWT");

        return new OpenAPI()
                // Security 인증 컴포넌트 설정
                .components(new Components().addSecuritySchemes("JWT", bearerAuth))
                // API 마다 Security 인증 컴포넌트 설정
                .addSecurityItem(addSecurityItem)
                .servers(Arrays.asList(
                        new Server().url("http://13.124.95.110:8080"),
                        new Server().url("http://localhost:8080")
                ))
                .info(info);
    }


    public ApiResponse createApiResponse(String message, Content content){
        return new ApiResponse().description(message).content(content);
    }

    @Bean
    public GlobalOpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> {
            // 공통으로 사용되는 response 설정
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();
                apiResponses.addApiResponse("200", createApiResponse(apiResponses.get("200").getDescription(), apiResponses.get("200").getContent()));
                apiResponses.addApiResponse("400", createApiResponse(apiResponses.get("400").getDescription(), apiResponses.get("400").getContent()));
                apiResponses.addApiResponse("401", createApiResponse(apiResponses.get("401").getDescription(), apiResponses.get("401").getContent()));
                apiResponses.addApiResponse("403", createApiResponse(apiResponses.get("403").getDescription(), apiResponses.get("403").getContent()));
                apiResponses.addApiResponse("404", createApiResponse(apiResponses.get("404").getDescription(), apiResponses.get("404").getContent()));
                apiResponses.addApiResponse("409", createApiResponse(apiResponses.get("409").getDescription(), apiResponses.get("409").getContent()));
                apiResponses.addApiResponse("500", createApiResponse(apiResponses.get("500").getDescription(), apiResponses.get("500").getContent()));
            }));
        };
    }

}
