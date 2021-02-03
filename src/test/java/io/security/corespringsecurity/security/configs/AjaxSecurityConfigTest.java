package io.security.corespringsecurity.security.configs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.security.corespringsecurity.security.filter.AjaxLoginProcessingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@SpringBootTest
class AjaxSecurityConfigTest {

    @Mock
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void mockSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void ajaxLoginTest_Success() throws Exception {
        mockMvc.perform(post("/api/login")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .header(AjaxLoginProcessingFilter.X_REQUEST_WITH,
                AjaxLoginProcessingFilter.XML_HTTP_REQUEST)
        ).andExpect(status().isOk())
            .andDo(print())
        ;
    }

}