package com.api.flujoefectivo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootTreeDTO {
    private Long idRoot;
    private String name;
    private BigDecimal total;
    private List<RootTreeDTO> children;
}
