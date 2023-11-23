package com.api.flujoefectivo.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAccount;
    @NotBlank(message = "Please add name")
    private String name;

    @Length(min=3, max = 100)
    private String code;

    private String description;
    private BigDecimal total;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name= "id_preceding",
            referencedColumnName = "idPreceding"
    )
    private PrecedingAccount precedingAccount;


}
