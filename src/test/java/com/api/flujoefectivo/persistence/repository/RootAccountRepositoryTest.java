package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.RootAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)*/
@SpringBootTest
class RootAccountRepositoryTest {

    @Autowired
    RootAccountRepository rootAccountRepository;
    /*@Autowired
    TestEntityManager testEntityManager;*/

    /*@BeforeEach
    void setUp() {
        RootAccount rootAccount = RootAccount.builder()
                .name("capital")
                .description("dinero de la empresa")
                .total(new BigDecimal(100))
                .build();
        testEntityManager.persist(rootAccount);
    }*/

    @Test
    public void updateTotalOfAll(){
        rootAccountRepository.updateTotalOfAll(new BigDecimal(0));
    }
}