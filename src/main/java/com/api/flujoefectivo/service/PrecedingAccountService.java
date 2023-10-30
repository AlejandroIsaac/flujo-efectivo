package com.api.flujoefectivo.service;

import com.api.flujoefectivo.persistence.entity.PrecedingAccount;

import java.util.List;

public interface PrecedingAccountService {
    List<PrecedingAccount> getAll();
    PrecedingAccount save(PrecedingAccount precedingAccount);
    void deleteById(Long id);
    Boolean existById(Long id);
}
