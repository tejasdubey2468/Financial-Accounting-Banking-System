package com.tejas.bankingsystem.service;

import com.tejas.bankingsystem.entity.Account;
import com.tejas.bankingsystem.entity.Transaction;
import com.tejas.bankingsystem.repository.AccountRepository;
import com.tejas.bankingsystem.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // ===================== CRUD =====================

    public Transaction createTransaction(Integer id, Transaction transaction) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));

        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Integer id) {

        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TRANSACTION NOT FOUND"));
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    // ===================== DEPOSIT =====================

    public String deposit(Integer accountId, double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionType("DEPOSIT");
        transaction.setAmount(amount);
        transaction.setDate(LocalDate.parse(LocalDate.now().toString()));
        transaction.setAccount(account);

        transactionRepository.save(transaction);

        return "Amount Deposited Successfully";
    }

    // ===================== WITHDRAW =====================

    public String withdraw(Integer accountId, double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account Not Found"));

        if (account.getBalance() < amount) {
            return "Insufficient Balance";
        }


        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionType("WITHDRAW");
        transaction.setAmount(amount);
        transaction.setDate(LocalDate.parse(LocalDate.now().toString()));
        transaction.setAccount(account);

        transactionRepository.save(transaction);

        return "Amount Withdrawn Successfully";
    }

    // ===================== HISTORY =====================

    public List<Transaction> getTransactionHistory(long accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("ACCOUNT NOT FOUND");
        }

        return transactionRepository.findByAccount(account);
    }

}