package com.api.flujoefectivo.controller;

import com.api.flujoefectivo.error.AccountNotFoundException;
import com.api.flujoefectivo.persistence.entity.Account;
import com.api.flujoefectivo.service.AccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountServiceImpl accountService;

    @GetMapping
    List<Account> getAll(){

        return accountService.getAll();
    }

    @GetMapping("/name/{name}")
    List<Account> getByNameIgnoreCase(@PathVariable String name){
        return accountService.findByNameIgnoreCase(name);
    }

    @GetMapping("/id/{id}")
    Account getById(@PathVariable Long id) throws AccountNotFoundException {
        return accountService.getById(id);
    }

    /*{

		"name": "Ferrepaz",
		"code": 101,
		"description": "provedor atlacomulco",
		"total": 0.0,
		"precedingAccount": {
			"idPreceding": 5,
			"name": "Cuentas por pagar",
			"code": "001",
			"description": "Cuentas por pagar",
			"total": 1000.0,
			"rootAccount": {
				"idRoot": 2,
				"name": "Pasivos",
				"description": "Cuenta de pasivos",
				"total": 100.0
		}
	}
	}*/
    @PostMapping
    ResponseEntity<Account> save(@Valid @RequestBody Account account){
        try{
            //if(account.getId() == null) {
                return new ResponseEntity(accountService.save(account), HttpStatus.CREATED);
            //}else return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    /*{
		"name": "Ferrepaz",
		"code": 101,
		"description": "provedor atlacomulco",
		"total": 0.0,
		"precedingAccount": {
			"idPreceding": 5,
			"name": "Cuentas por pagar",
			"code": "001",
			"description": "Cuentas por pagar",
			"total": 1000.0,
			"rootAccount": {
				"idRoot": 2,
				"name": "Pasivos",
				"description": "Cuenta de pasivos",
				"total": 100.0
		}
	}
	}*/
    @PutMapping
    ResponseEntity<Account> update(@RequestBody Account account){
        if(accountService.existById(account.getId())){
            return new ResponseEntity(accountService.update(account), HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id){
        if(accountService.existById(id)){
            accountService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}
