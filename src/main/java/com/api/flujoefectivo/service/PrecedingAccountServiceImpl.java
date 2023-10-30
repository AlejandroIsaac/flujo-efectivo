package com.api.flujoefectivo.service;

import com.api.flujoefectivo.persistence.entity.PrecedingAccount;
import com.api.flujoefectivo.persistence.repository.PrecedingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecedingAccountServiceImpl implements PrecedingAccountService{
    @Autowired
    PrecedingAccountRepository precedingAccountRepository;

    @Override
    public List<PrecedingAccount> getAll() {
        return precedingAccountRepository.findAll();
    }

    @Override
    public PrecedingAccount save(PrecedingAccount precedingAccount) {
        return precedingAccountRepository.save(precedingAccount);
    }

    @Override
    public void deleteById(Long id) {
        precedingAccountRepository.deleteById(id);
    }

    @Override
    public Boolean existById(Long id) {
        return precedingAccountRepository.existsById(id);
    }
}
