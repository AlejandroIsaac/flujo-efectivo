package com.api.flujoefectivo.controller;


import com.api.flujoefectivo.persistence.entity.RootAccount;
import com.api.flujoefectivo.service.RootAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rootAccount")
public class RootAccountController {

    @Autowired
    private RootAccountService rootAccountService;

    @GetMapping
    public List<RootAccount> getAll(){
        return rootAccountService.findAll();
    }

    @GetMapping("byName/{name}")
    ResponseEntity<Optional<RootAccount>> getByNameIgnoreCase(@PathVariable String name){
        if (rootAccountService.findByNameIgnoreCase(name).isPresent()){
            return new ResponseEntity(rootAccountService.findByNameIgnoreCase(name), HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    RootAccount save(@RequestBody RootAccount rootAccount){
        return rootAccountService.save(rootAccount);
    }

    @PutMapping
    ResponseEntity<RootAccount> update(@RequestBody RootAccount rootAccount){
        if (rootAccountService.existById(rootAccount.getIdRoot())){
            return new ResponseEntity(rootAccountService.update(rootAccount), HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteById(@PathVariable Long id){
        if(rootAccountService.existById(id)){
            rootAccountService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        }else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }



}
