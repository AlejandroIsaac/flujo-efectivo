package com.api.flujoefectivo.controller;

import com.api.flujoefectivo.persistence.entity.RootAccount;
import com.api.flujoefectivo.service.RootAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RootAccountController.class)
class RootAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RootAccountService rootAccountService;
    RootAccount rootAccount;

    @BeforeEach
    void setUp() {
        rootAccount=
                RootAccount.builder()
                        .idRoot(1L)
                        .name("capital")
                        .description("ganancia")
                        .total(new BigDecimal(10))
                        .build();

    }

    //cuando mande un entity sin id debe devolver mismo entirty con id
    @Test
    public void saveRootAccount() throws Exception{
        RootAccount rootAccountPost=
                RootAccount.builder()
                        .name("capital")
                        .description("ganancia")
                        .total(new BigDecimal(140))
                        .build();
        Mockito.when(rootAccountService.save(rootAccountPost)).thenReturn(rootAccount);

    mockMvc.perform(post("/rootAccount").
            contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "\"name\":\"capital\",\n" +
                    "\"description\":\"ganancia\",\n" +
                    "\"total\":10.0\n" +
                    "}"))
            .andExpect(status().isOk());
    }


}