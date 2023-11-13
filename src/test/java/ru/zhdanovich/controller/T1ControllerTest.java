package ru.zhdanovich.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.zhdanovich.service.T1Service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(T1Controller.class)
public class T1ControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private T1Service service;

    @Test
    public void given_requestWithStrParam_when_perform_then_OK() throws Exception {
        mockService();
        var result = mockMvc.perform(
                get("http://localhost:8080/api/v1/get")
                        .param("str", "aaaaabcccc")
                )
                .andExpect(status().isOk())
                .andReturn();

        String expected = "“a”: 5, “c”: 4, “b”: 1";

        Assertions.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void given_requestWithEmptyStrParam_when_perform_then_BadRequest() throws Exception {
        var result = mockMvc.perform(
                        get("http://localhost:8080/api/v1/get")
                                .param("str", "")
                )
                .andExpect(status().isBadRequest())
                .andReturn();

        String expected = "{str} param is empty";

        Assertions.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void given_requestWithoutStrParam_when_perform_then_BadRequest() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/v1/get"))
                .andExpect(status().isBadRequest());
    }

    private void mockService() {
        Mockito.when(service.parseString(Mockito.anyString())).thenReturn("“a”: 5, “c”: 4, “b”: 1");
    }
}
