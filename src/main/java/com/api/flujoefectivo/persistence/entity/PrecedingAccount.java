package com.api.flujoefectivo.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preceding_account")
public class PrecedingAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_preceding")
    private Long idPreceding;

    private String name;
    private String code;
    private String description;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_root_account", insertable = false)
    private RootAccount rootAccount;

}
