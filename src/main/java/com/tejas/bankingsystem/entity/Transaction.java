package com.tejas.bankingsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private String transactionType;
    private Double amount;
    private LocalDate date;


    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public Transaction(){

    }
    public Transaction(Integer transactionId, String transactionType, double amount, LocalDate date) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setAccount(Account account) {

    }
}
