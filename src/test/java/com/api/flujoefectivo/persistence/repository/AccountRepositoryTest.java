package com.api.flujoefectivo.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void updateTotalByName(){
        String name= "Ferrepaz";
        BigDecimal balance= new BigDecimal(50);

        accountRepository.updateTotalByName(name, balance);
    }
    @Test
    public void updateTotalOfAll(){
        accountRepository.updateTotalOfAll(new BigDecimal(0));
    }
}