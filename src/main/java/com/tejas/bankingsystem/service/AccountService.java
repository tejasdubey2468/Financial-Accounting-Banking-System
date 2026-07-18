package com.tejas.bankingsystem.service;

import com.tejas.bankingsystem.entity.Account;
import com.tejas.bankingsystem.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }
     // user ko create kar diya
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public List<Account> getAllAccount(){
        return accountRepository.findAll() ;
    }
    public Account getAccountById(Integer id){
        return accountRepository.findById(id).orElse(null);
    }
    public Account updateAccount(Integer id , Account updatedAccount){
        Account account = accountRepository.findById(id).orElse(null);

        if(account != null){
            account.setUser(updatedAccount.getUser());
            account.setAccountType(updatedAccount.getAccountType());
            account.setBalance(updatedAccount.getBalance());
            account.setBranchName(updatedAccount.getBranchName());

            return accountRepository.save(account);
        }
        return null;
    }
    public void deleteAccount(Integer id){
       accountRepository.deleteById(id);
    }
}
