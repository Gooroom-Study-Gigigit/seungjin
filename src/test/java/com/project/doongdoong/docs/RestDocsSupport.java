package com.project.doongdoong.docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
public abstract class RestDocsSupport {

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 스프링 서버 자체를 띄우는 방법과 문서 자체로 하는 경우로 총 2가지가 존재한다.
     * 스프링 서버를 띄우는 건 올라가는데 추가적인 시간 비용이 들기 때문에 없이 하는 것이 더 합리적인다.
     * 그렇기에 파라미터도 provider 하나 뿐이다.
     * @param provider
     */
    @BeforeEach
    void setUp(RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(initController())
                .apply(documentationConfiguration(provider))
                .build();
    }

    /**
     * 컨틀롤러를 주입해주는 메서드이다. 위 메서드에 모든 컨트롤러를 하나하나 넣어주기 불편하니
     * 메서드로 추상화 한다.
     */
    protected abstract Object initController();

}