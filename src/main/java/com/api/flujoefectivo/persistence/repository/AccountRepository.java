package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    List<Account> findByNameIgnoreCase(String name);

}
