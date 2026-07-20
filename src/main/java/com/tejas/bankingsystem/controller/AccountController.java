package com.tejas.bankingsystem.controller;

import com.tejas.bankingsystem.entity.Account;
import com.tejas.bankingsystem.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//controll layers
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    @PostMapping
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping
    public List<Account> getAllAccount(){
        return accountService.getAllAccount();
    }

    @GetMapping("/{id}")
    public Account getAccountById (@PathVariable Integer id){
        return accountService.getAccountById(id);
    }
    @PutMapping("/{id}")
    public Account updateAccount (@PathVariable Integer id ,
                                  @RequestBody Account account){
        return accountService.updateAccount(id , account);
    }
    @DeleteMapping("/{id}")
    public  void removeAccountById(@PathVariable Integer id){
         accountService.deleteAccount(id);

    }

}
