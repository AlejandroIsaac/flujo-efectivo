package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.RootAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RootAccountRepository extends JpaRepository<RootAccount, Long> {

    Optional<RootAccount> findByNameIgnoreCase(String name);
}
