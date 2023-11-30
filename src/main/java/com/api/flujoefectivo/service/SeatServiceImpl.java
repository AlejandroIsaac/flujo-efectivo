package com.api.flujoefectivo.service;

import com.api.flujoefectivo.persistence.entity.Account;
import com.api.flujoefectivo.persistence.entity.PrecedingAccount;
import com.api.flujoefectivo.persistence.entity.RootAccount;
import com.api.flujoefectivo.persistence.entity.Seat;
import com.api.flujoefectivo.persistence.repository.AccountRepository;
import com.api.flujoefectivo.persistence.repository.PrecedingAccountRepository;
import com.api.flujoefectivo.persistence.repository.RootAccountRepository;
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
    RootAccountRepository rootAccountRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PrecedingAccountRepository precedingAccountRepository;

    @Override
    public Seat add(Seat seat) {
        Long idAccount = seat.getAccountID().getIdAccount();
        RootAccount rootAccount; //raiz de Account
        RootAccount rootAccountTransfer; //raiz de TranferAccount
        Long idAccountTransfer= seat.getTransferAccount() != null ?
                seat.getTransferAccount().getIdAccount() : seat.getTransferPrecedingAccount().getIdPreceding();
        //obtener rootAccount del Campo AccountId
        if(accountRepository.existsById(idAccount)){
            Account account = accountRepository.findById(idAccount).get();
            rootAccount =account.getPrecedingAccount().getRootAccount();
        }else {//si el campo AccountId es de tipo PrecedingAccount(al parecer nunca entrara aqui)
            PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
            rootAccount = precedingAccount.getRootAccount();
        }

        //obtener rootAccount del campo seat.transferaccount(puedes ser de tipo Account o PrecedingAccount)
        if(accountRepository.existsById(idAccountTransfer)){
            Account account = accountRepository.findById(idAccountTransfer).get();
            rootAccountTransfer =account.getPrecedingAccount().getRootAccount();
        }else {
            PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccountTransfer).get();
            rootAccountTransfer = precedingAccount.getRootAccount();
        }

        //obtener el valor que viene en seat.debe o seat.haber
        //compareTo Returns: -1, 0, or 1 as this BigDecimal is numerically less than, equal to, or greater than val.
        //max Returns the maximum of this BigDecimal and val.
        BigDecimal debe =seat.getDebe();
        BigDecimal Max = seat.getDebe().max(seat.getHaber());
        BigDecimal montoImportante;
        if(Max.compareTo(debe) == 0){
            montoImportante = seat.getDebe();

        }else {
            montoImportante = seat.getHaber();
        }
        //definir comportamineto debo o haber para accountId y para tranferAccount
        String debeOhaberAccount = Max.compareTo(debe) == 0 ? "debe" : "haber";
        String debeOhaberAccountTransfer = debeOhaberAccount.equals("haber") ? "debe" : "haber";


        //Modificar el total de ambas cuentas segun su debe o haber
        BigDecimal balanceTotal;
        BigDecimal balanceTotalRoot;
        //modifica account si el monto llega por el campo debe
        if (debeOhaberAccount.equals("debe")){
            String behaviorDebe = rootAccount.getDebe();
            switch (behaviorDebe){
                case "+":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().add(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().add(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }
                    break;
                default:
                    System.out.println("Debe y haber solo pueden ser + o -");
            }
            //modifica account si el monto llega por el campo haber
        }else if(debeOhaberAccount.equals("haber")) {
            String behaviorHaber = rootAccount.getHaber();
            switch (behaviorHaber){
                case "+":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().add(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().add(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccount)){
                        Account account =accountRepository.findById(idAccount).get();
                        balanceTotal = account.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(account.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccount)){
                        PrecedingAccount precedingAccount = precedingAccountRepository.findById(idAccount).get();
                        balanceTotal = precedingAccount.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccount.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccount.getName(),balanceTotal);
                        //actualiza el total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccount.getName(),balanceTotalRoot);
                    }
                    break;
                default:
                    System.out.println("Debe y haber solo pueden ser + o -");
            }
        }

        //modifica accountTranfer si el monto llega por le debe
        if (debeOhaberAccountTransfer.equals("debe")){
            String behaviorDebe = rootAccountTransfer.getDebe();
            switch (behaviorDebe){
                case "+":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account accountTransfer =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = accountTransfer.getTotal().add(montoImportante);
                        balanceTotalRoot= rootAccountTransfer.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(accountTransfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccountTransfer = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccountTransfer.getTotal().add(montoImportante);
                        balanceTotalRoot= rootAccountTransfer.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccountTransfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account accountTransfer =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = accountTransfer.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccountTransfer.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(accountTransfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccountTranfer = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccountTranfer.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccountTransfer.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccountTranfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
                    }
                    break;
                default:
                    System.out.println("Debe y haber solo pueden ser + o -");
            }
            //modifica accountTranfer si el monto llega por le haber
        }else if(debeOhaberAccountTransfer.equals("haber")) {
            String behaviorHaber = rootAccountTransfer.getHaber();
            switch (behaviorHaber){
                case "+":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account accountTransfer =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = accountTransfer.getTotal().add(montoImportante);
                        balanceTotalRoot = rootAccountTransfer.getTotal().add(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(accountTransfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccountTransfer = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccountTransfer.getTotal().add(montoImportante);
                        balanceTotalRoot = rootAccountTransfer.getTotal().add(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccountTransfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
                    }
                    break;
                case "-":
                    if(accountRepository.existsById(idAccountTransfer)){
                        Account accountTransfer =accountRepository.findById(idAccountTransfer).get();
                        balanceTotal = accountTransfer.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccountTransfer.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        accountRepository.updateTotalByName(accountTransfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
                    }else if(precedingAccountRepository.existsById(idAccountTransfer)){
                        PrecedingAccount precedingAccountTransfer = precedingAccountRepository.findById(idAccountTransfer).get();
                        balanceTotal = precedingAccountTransfer.getTotal().subtract(montoImportante);
                        balanceTotalRoot = rootAccountTransfer.getTotal().subtract(montoImportante);
                        //actualiza la DB
                        precedingAccountRepository.updateTotalByName(precedingAccountTransfer.getName(),balanceTotal);
                        //actualiza total de Root en la DB
                        rootAccountRepository.updateTotalByName(rootAccountTransfer.getName(), balanceTotalRoot);
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
