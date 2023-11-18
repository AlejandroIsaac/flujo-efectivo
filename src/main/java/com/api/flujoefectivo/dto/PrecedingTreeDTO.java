package com.api.flujoefectivo.dto;

import com.api.flujoefectivo.persistence.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrecedingTreeDTO {
    private Long idPreceding;
    private String name;
    private BigDecimal total;
    private List<AccountTreeDTO> accountList;
}
