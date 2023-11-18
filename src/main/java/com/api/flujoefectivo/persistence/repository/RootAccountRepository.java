package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.RootAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface RootAccountRepository extends JpaRepository<RootAccount, Long> {

    Optional<RootAccount> findByNameIgnoreCase(String name);

    @Transactional
    @Modifying
    @Query(
            nativeQuery = true,
            value = "update root_account set total =:balanceTotal"
    )
    public void updateTotalOfAll(@Param(value = "balanceTotal") BigDecimal balanceTotal);
}
