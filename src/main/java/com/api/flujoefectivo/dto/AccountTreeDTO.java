package com.api.flujoefectivo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTreeDTO {
    private Long idAccount;
    private String name;
    private BigDecimal total;
}
