package com.api.flujoefectivo.service;

import com.api.flujoefectivo.persistence.entity.RootAccount;
import com.api.flujoefectivo.persistence.repository.RootAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RootAccountServiceImplTest {

    @Autowired
    RootAccountService rootAccountService;

    @MockBean
    RootAccountRepository rootAccountRepository;


    @BeforeEach
    void setUp() {
        RootAccount rootAccount = RootAccount.builder()
                .name("Capital")
                .description("guarada el capital")
                .total(10.0)
                .build();
        //cuando llamen a rootAccountRepository simula que consulta a la DB entonces devulve el rootAccount creado.
        Mockito.when(rootAccountRepository.findByNameIgnoreCase("Capital")).thenReturn(Optional.of(rootAccount));
    }

    @Test
    @DisplayName("Prueba de obtener un RootAccount a partir de una name valido")
    void findByNameIgnoreCaseFound() {
        String nameTest= "Capital";
        RootAccount rootAccount= rootAccountService.findByNameIgnoreCase(nameTest).get();

        assertEquals(nameTest, rootAccount.getName());
    }
}