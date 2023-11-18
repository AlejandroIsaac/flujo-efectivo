package com.api.flujoefectivo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SeatServiceImplTest {

    @Test
    public void testBigDecimal(){
        BigDecimal number1 = new BigDecimal(1);
        BigDecimal number2 = new BigDecimal(20);
        BigDecimal totalSuma = number1.add(number2);
        System.out.println("Suma "+totalSuma.toString());

        BigDecimal totalResta = number2.subtract(number1);
        System.out.println("Resta " + totalResta);
    }

}