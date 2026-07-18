package com.tejas.bankingsystem.repository;

import com.tejas.bankingsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface AccountRepository extends JpaRepository<Account, Integer>{
    Account findByAccountNumber(long accountNumber);
    }

