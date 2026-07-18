package com.tejas.bankingsystem.repository;

import com.tejas.bankingsystem.entity.Account;
import com.tejas.bankingsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
    List<Transaction> findByAccount(Account account);
    }

