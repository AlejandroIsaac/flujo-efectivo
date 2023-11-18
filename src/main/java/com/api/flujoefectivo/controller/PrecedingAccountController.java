package com.api.flujoefectivo.controller;

import com.api.flujoefectivo.persistence.entity.PrecedingAccount;
import com.api.flujoefectivo.service.PrecedingAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/precedingAccount")
public class PrecedingAccountController {

    @Autowired
    PrecedingAccountServiceImpl precedingAccountService;

    @GetMapping
    List<PrecedingAccount> getAll(){
        return precedingAccountService.getAll();
    }

    /*
    * {
	"name": "Activo circulante",
    "description": "Activo circulante",
    "total": 150000.0,
	  "rootAccount": {
			"idRoot": 1,
			"name": "Activos",
			"description": "Cuenta de activos",
			"total": 0.0
		}
	}*/
    @PostMapping
    ResponseEntity<PrecedingAccount> save(@RequestBody PrecedingAccount precedingAccount){
        if(precedingAccount.getIdPreceding() == null){
            return new ResponseEntity(precedingAccountService.save(precedingAccount), HttpStatus.CREATED);
        }else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    ResponseEntity<PrecedingAccount> update(@RequestBody PrecedingAccount precedingAccount){
        if (precedingAccountService.existById(precedingAccount.getIdPreceding())){
            return new ResponseEntity(precedingAccountService.save(precedingAccount),HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id){
        if (precedingAccountService.existById(id)){
            precedingAccountService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/rootAccount/{idRoot}")
    ResponseEntity<List<PrecedingAccount>> getByIdRoot(@PathVariable Long idRoot){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(precedingAccountService.getByRootAccount(idRoot));
    }
}

/*{
	"name": "Cuentas por pagar",
    "code": "963",
    "description": "Cuentas por pagar",
    "total": 150000.0,
	"rootAccount": {"idRoot": 2}
}*/