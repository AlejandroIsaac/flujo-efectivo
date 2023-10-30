package com.api.flujoefectivo.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please add name")
    private String name;

    @Length(min=3, max = 100)
    private String code;

    private String description;
    private Double total;

    @ManyToOne
    @JoinColumn(name= "id_preceding")
    private PrecedingAccount precedingAccount;

}
