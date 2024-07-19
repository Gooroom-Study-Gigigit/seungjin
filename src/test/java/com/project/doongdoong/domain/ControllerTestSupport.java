package com.project.doongdoong.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.doongdoong.domain.counsel.controller.CounselController;
import com.project.doongdoong.domain.counsel.service.CounselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = CounselController.class /* 추가로 필요한 컨트롤러 클래스 지정 */)
public abstract class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected CounselService counselService;

    @Autowired
    protected ObjectMapper objectMapper;
}
