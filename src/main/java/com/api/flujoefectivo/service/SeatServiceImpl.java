package com.api.flujoefectivo.service;

import com.api.flujoefectivo.persistence.entity.Account;
import com.api.flujoefectivo.persistence.entity.PrecedingAccount;
import com.api.flujoefectivo.persistence.entity.RootAccount;
import com.api.flujoefectivo.persistence.entity.Seat;
import com.api.flujoefectivo.persistence.repository.AccountRepository;
import com.api.flujoefectivo.persistence.repository.PrecedingAccountRepository;
import com.api.flujoefectivo.persistence.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class SeatServiceImpl implements SeatService{
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PrecedingAccountRepository precedingAccountRepository;

    @Override
    public Seat add(Seat seat) {
        Long idAccount = seat.getAccountID().getIdAccount();
        RootAccount rootAccount;
        Long idAccountTransfer= seat.getTransferAccount() != null ?
                seat.getTransferAccount().getIdAccount() : seat.getTransferPrecedingAccount().getIdPreceding();
        RootAccount rootAccountTransfer;
        //obtener root de account
        if(accountRepository.existsById(idAccount)){
            Account account = accountRepository.findById(idAccount).get();
            rootAccount =account.getPrecedingAccount().getRootAccount();
        }else {
            PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
            rootAccount = precedingAccount.getRootAccount();
        }
        //obtener root de transferaccount
        if(accountRepository.existsById(idAccountTransfer)){
            Account account = accountRepository.findById(idAccountTransfer).get();
            rootAccountTransfer =account.getPrecedingAccount().getRootAccount();
        }else {
            PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccountTransfer).get();
            rootAccountTransfer = precedingAccount.getRootAccount();
        }
        //obtener comportamiento a travez del debe o haber
        BigDecimal debe =seat.getDebe();
        BigDecimal Max = seat.getDebe().max(seat.getHaber());

        BigDecimal montoImportante;
        if(Max.compareTo(debe) == 0){
            montoImportante = seat.getDebe();

        }else {
            montoImportante = seat.getHaber();
        }
        String debeOhaberAccount = Max.compareTo(debe) == 0 ? "debe" : "haber";
        String debeOhaberAccountTransfer = debeOhaberAccount.equals("haber") ? "debe" : "haber";


        //Modificar ambas cuentas segun su debe o haber
        BigDecimal balanceTotal;
        //modifica account
        if (debeOhaberAccount.equals("debe")){
            String behaviorDebe = rootAccount.getDebe();
            switch (behaviorDebe){
                case "+":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                default:
                    System.out.println("Debe y haber solo pueden ser + o -");
            }

        }else if(debeOhaberAccount.equals("haber")) {
            String behaviorHaber = rootAccount.getHaber();
            switch (behaviorHaber){
                case "+":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                default:
                    System.out.println("Debe y haber solo pueden ser + o -");
            }
        }
        //modifica accountTranfer
        if (debeOhaberAccountTransfer.equals("debe")){
            String behaviorDebe = rootAccountTransfer.getDebe();
            switch (behaviorDebe){
                case "+":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account account =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = account.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account account =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = account.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                default:
                    System.out.println("Debe y haber solo pueden ser + o -");
            }

        }else if(debeOhaberAccountTransfer.equals("haber")) {
            String behaviorHaber = rootAccountTransfer.getHaber();
            switch (behaviorHaber){
                case "+":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account account =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = account.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account account =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = account.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                    }
                    break;
                default:
                    System.out.println("Debe y haber solo pueden ser + o -");
            }
        }

        return seatRepository.save(seat);
    }

    @Override
    public List<Seat> getAll() {
        return seatRepository.findAll();
    }

    @Override
    public Seat getById(Long idSeat) {
        return seatRepository.findById(idSeat).get();
    }

    @Override
    public Seat updateById(Seat seat) {
        if(seatRepository.existsById(seat.getIdSeat())){
            return seatRepository.save(seat);
        }
        return seat;
    }

    //logica de partida doble
    /*public static void balanceAccounts(Long idAccount, Long idTransfersAccount){
        AccountRepository accountRepository;
        Account account = accountRepository.findById(idAccount).get();
    }*/
}
