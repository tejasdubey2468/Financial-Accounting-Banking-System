package com.tejas.bankingsystem.controller;

import com.tejas.bankingsystem.entity.Transaction;
import com.tejas.bankingsystem.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService= transactionService;
    }

    @PostMapping("/{accountId}")
    public Transaction createTransaction (@PathVariable Integer accountId, @RequestBody Transaction transaction){
        return transactionService.createTransaction(accountId ,transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransaction (){
        return transactionService.getAllTransaction();
    }

    @GetMapping("/{id}")
    public Transaction getAllTransactionById(@PathVariable Integer id){
        return transactionService.getTransactionById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteTransactionById(@PathVariable Integer id){
        transactionService.deleteTransaction(id);
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Integer accountId,
                               @RequestParam double amount) {
        return transactionService.deposit(accountId, amount);
    }
}
