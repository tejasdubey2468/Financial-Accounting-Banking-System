// Kaise so Tejas Bhai
package com.tejas.bankingsystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer accountId;
    private String user;
    private Long accountNumber;
    private String accountType;
    private Double balance;
    private String branchName;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Transaction> transactions;


    public Account(){

    }
    public Account(Integer accountId, String user, long accountNumber, String accountType, Double balance, String branchName) {
        this.accountId = accountId;
        this.user = user;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.branchName = branchName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
