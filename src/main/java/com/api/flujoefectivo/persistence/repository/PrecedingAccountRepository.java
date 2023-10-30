package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.PrecedingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecedingAccountRepository extends JpaRepository<PrecedingAccount, Long> {

}
