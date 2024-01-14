package com.imlinker.coreapi.docs.system;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.imlinker.coreapi.core.system.SystemController;
import com.imlinker.coreapi.docs.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

public class SystemControllerRestDocsTest extends RestDocsSupport {
    @Override
    protected Object initController() {
        return new SystemController();
    }

    @Test
    public void REST_DOCS_에러타입을_반환할_수_있다() throws Exception {
        mockMvc
                .perform(get("/error-types").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "error-types",
                                responseFields(
                                        fieldWithPath("responseType").type(JsonFieldType.STRING).description("결과 상태"),
                                        fieldWithPath("data").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).description("에러 데이터"),
                                        fieldWithPath("debug").type(JsonFieldType.NULL).description("디버깅 데이터"),
                                        fieldWithPath("data.[].code").type(JsonFieldType.STRING).description("에러 코드"),
                                        fieldWithPath("data.[].message")
                                                .type(JsonFieldType.STRING)
                                                .description("에러 메시지"),
                                        fieldWithPath("data.[].status")
                                                .type(JsonFieldType.NUMBER)
                                                .description("HTTP 상태 코드"))));
    }
}
