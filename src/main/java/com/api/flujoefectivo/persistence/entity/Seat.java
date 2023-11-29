package com.api.flujoefectivo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Seat {
    @SequenceGenerator(
            name = "seat_sequence",
            sequenceName = "seat_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @Id
    @GeneratedValue(
            generator = "seat_sequence",
            strategy = GenerationType.SEQUENCE)
    private Long idSeat;

    private String date;
    private Long number;
    private String description;

    @ManyToOne(
            cascade = CascadeType.MERGE
            )
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "idAccount")
    private Account accountID;

    private BigDecimal debe;
    private BigDecimal haber;
    //puedo o no guardar data
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "transfer_account_id",
            referencedColumnName = "idAccount")
    private Account transferAccount;
    //puedo o no guardar data
    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "transfer_preceding_account_id",
            referencedColumnName = "idPreceding")
    private PrecedingAccount transferPrecedingAccount;
}
