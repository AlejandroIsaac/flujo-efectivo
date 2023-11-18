package com.api.flujoefectivo.service;

import com.api.flujoefectivo.dto.RootTreeDTO;
import com.api.flujoefectivo.persistence.entity.RootAccount;

import java.util.List;
import java.util.Optional;

public interface RootAccountService {
    List<RootAccount> findAll();
    RootAccount save(RootAccount rootAccount);
    RootAccount update(RootAccount rootAccount);
    Boolean existById(Long id);
    void deleteById(Long id);
    Optional<RootAccount> findByNameIgnoreCase(String name);

    List<RootTreeDTO> getRootTree();
}
