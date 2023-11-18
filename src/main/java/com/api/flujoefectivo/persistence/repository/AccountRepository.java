package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByNameIgnoreCase(String name);

    @Query("select a from Account a where a.precedingAccount.idPreceding = ?1")
    List<Account> findByPrecedingAccountIdPreceding(Long idPreceding);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "update account set total =:balanceTotal where name=:nameAccount"
    )
    public void updateTotalByName(@Param(value = "nameAccount") String nameAccount,@Param(value = "balanceTotal") BigDecimal balanceTotal);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "update account set total =:balanceTotal"
    )
    public void updateTotalOfAll(@Param(value = "balanceTotal") BigDecimal balanceTotal);


}
