package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.PrecedingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrecedingAccountRepository extends JpaRepository<PrecedingAccount, Long> {
    //get by precedingAccountId
    List<PrecedingAccount> findByRootAccountIdRoot (Long idRoot);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "update preceding_account set total=:balanceTotal where name=:namePreceding"
    )
    public void updateTotalByName(String namePreceding, BigDecimal balanceTotal);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "update preceding_account set total =:balanceTotal"
    )
    public void updateTotalOfAll(@Param(value = "balanceTotal") BigDecimal balanceTotal);
}
