package com.api.flujoefectivo.service;

import com.api.flujoefectivo.dto.AccountTreeDTO;
import com.api.flujoefectivo.dto.PrecedingTreeDTO;
import com.api.flujoefectivo.dto.RootTreeDTO;
import com.api.flujoefectivo.persistence.entity.Account;
import com.api.flujoefectivo.persistence.entity.PrecedingAccount;
import com.api.flujoefectivo.persistence.entity.RootAccount;
import com.api.flujoefectivo.persistence.repository.AccountRepository;
import com.api.flujoefectivo.persistence.repository.PrecedingAccountRepository;
import com.api.flujoefectivo.persistence.repository.RootAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RootAccountServiceImpl implements RootAccountService{

    @Autowired
    private RootAccountRepository rootAccountRepository;
    @Autowired
    private PrecedingAccountRepository precedingAccountRepository;
    @Autowired
    private AccountRepository accountRepository;

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

    @Override
    public List<RootTreeDTO> getRootTree() {
        //obtiene todas la raizes
        List<RootAccount> rootAccounts= rootAccountRepository.findAll();

        List<RootTreeDTO> rootTreeDTOS = new ArrayList<>();
        for (RootAccount root :rootAccounts) {
            RootTreeDTO rootTreeDTO = new RootTreeDTO();
            rootTreeDTO.setIdRoot(root.getIdRoot());
            rootTreeDTO.setName(root.getName());
            rootTreeDTO.setTotal(root.getTotal());

            //por cada raiz obtiene su preceding
            List<PrecedingTreeDTO> precedingTreeDTOS = new ArrayList<>();
            List<PrecedingAccount> precedingAccounts = precedingAccountRepository.findByRootAccountIdRoot(root.getIdRoot());
            for (PrecedingAccount preceding: precedingAccounts) {
                PrecedingTreeDTO precedingTreeDTO = new PrecedingTreeDTO();
                precedingTreeDTO.setIdPreceding(preceding.getIdPreceding());
                precedingTreeDTO.setName(preceding.getName());
                precedingTreeDTO.setTotal(preceding.getTotal());

            //por cada preceding obtiene sus accounts
                List<AccountTreeDTO> accountTreeDTOS = new ArrayList<>();
                List<Account> accounts = accountRepository.findByPrecedingAccountIdPreceding(preceding.getIdPreceding());
                for ( Account account :accounts) {
                    AccountTreeDTO accountTreeDTO = new AccountTreeDTO();
                    accountTreeDTO.setIdAccount(account.getIdAccount());
                    accountTreeDTO.setName(account.getName());
                    accountTreeDTO.setTotal(account.getTotal());
                    accountTreeDTOS.add(accountTreeDTO);
                }
                precedingTreeDTO.setAccountList(accountTreeDTOS);
                precedingTreeDTOS.add(precedingTreeDTO);
            }
            rootTreeDTO.setPrecedingAccountList(precedingTreeDTOS);

            rootTreeDTOS.add(rootTreeDTO);
        }
        return rootTreeDTOS;
    }
}
