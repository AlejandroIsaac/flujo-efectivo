package com.api.flujoefectivo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preceding_account")
public class PrecedingAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPreceding;

    private String name;
    private String code;
    private String description;
    private BigDecimal total;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "id_root_account",
            referencedColumnName = "idRoot"
            )
    private RootAccount rootAccount;

}
