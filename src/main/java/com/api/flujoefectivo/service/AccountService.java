package com.api.flujoefectivo.service;

import com.api.flujoefectivo.error.AccountNotFoundException;
import com.api.flujoefectivo.persistence.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAll();
    Account save(Account account);
    Account update(Account account);
    void deleteById(Long id);
    boolean existById(Long id);
    List<Account> findByNameIgnoreCase(String name);
    Account getById(Long id) throws AccountNotFoundException;
}
