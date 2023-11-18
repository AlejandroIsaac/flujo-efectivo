package com.api.flujoefectivo.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PrecedingAccountRepositoryTest {
    @Autowired
    PrecedingAccountRepository precedingAccountRepository;

    @Test
    public void updateTotalByName(){
        String namePreceding= "Capital contribuido";
        BigDecimal balanceTotal= new BigDecimal(800);
        precedingAccountRepository.updateTotalByName(namePreceding,balanceTotal);
    }
    @Test
    public void updateTotalOfAll(){
        precedingAccountRepository.updateTotalOfAll(new BigDecimal(0));
    }

}