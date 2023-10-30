package com.api.flujoefectivo.service;

import com.api.flujoefectivo.persistence.entity.RootAccount;
import com.api.flujoefectivo.persistence.repository.RootAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RootAccountServiceImpl implements RootAccountService{

    @Autowired
    private RootAccountRepository rootAccountRepository;

    @Override
    public List<RootAccount> findAll() {
        return rootAccountRepository.findAll();
    }

    @Override
    public RootAccount save(RootAccount rootAccount) {
        return rootAccountRepository.save(rootAccount);
    }

    @Override
    public RootAccount update(RootAccount rootAccount) {
        if (rootAccountRepository.existsById(rootAccount.getIdRoot())){
            return rootAccountRepository.save(rootAccount);
        }else return rootAccount;

    }

    @Override
    public Boolean existById(Long id) {
        return rootAccountRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        rootAccountRepository.deleteById(id);
    }

    @Override
    public Optional<RootAccount> findByNameIgnoreCase(String name) {

            return rootAccountRepository.findByNameIgnoreCase(name);


    }
}
