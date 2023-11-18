package com.api.flujoefectivo.service;

import com.api.flujoefectivo.error.AccountNotFoundException;
import com.api.flujoefectivo.persistence.entity.Account;
import com.api.flujoefectivo.persistence.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{


    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return accountRepository.existsById(id);
    }

    @Override
    public List<Account> findByNameIgnoreCase(String name) {
        return accountRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Account getById(Long id) throws AccountNotFoundException {
        Optional<Account> account= accountRepository.findById(id);

        if(!account.isPresent()){
            throw new AccountNotFoundException("Account not exist");
        }
        return accountRepository.findById(id).get();
    }

    @Override
    public List<Account> getByPrecedingId(Long idPreceding) {
        return accountRepository.findByPrecedingAccountIdPreceding(idPreceding);
    }
}
