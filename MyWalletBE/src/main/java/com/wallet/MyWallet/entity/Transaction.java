package com.wallet.MyWallet.entity;

import com.wallet.MyWallet.commons.enums.TransactionStatus;
import com.wallet.MyWallet.commons.enums.TransactionType;

import javax.persistence.*;

@Entity
@Table(name = ITransaction.TABLE_NAME)
public class Transaction implements ITransaction {

    @Id
    @Column(name = ITransaction.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = ITransaction.USER_EMAIL)
    private String userEmail;

    @Column(name = ITransaction.USER_ID)
    private Long userId;

    @Column(name = ITransaction.TRANSACTION_TYPE)
    private TransactionType transactionType;

    @Column(name = ITransaction.TRANSACTION_STATUS)
    private TransactionStatus transactionStatus;

    @Column(name = ITransaction.TRANSACTION_TIME)
    private Long transactionTime;

    @Column(name = ITransaction.TRANSACTION_AMOUNT)
    private int transactionAmount;

    @Column(name = ITransaction.CURRENT_BALANCE)
    private int currentBalance;

    @ManyToOne
    @JoinColumn(name = IAccount.ID)
    private Account account;

    public Transaction(){

    }

    public Transaction(String userEmail, Long userId, TransactionType transactionType, TransactionStatus transactionStatus,
                       Long transactionTime, int transactionAmount, int currentBalance) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.transactionTime = transactionTime;
        this.transactionAmount = transactionAmount;
        this.currentBalance = currentBalance;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public void setTransactionTime(long transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public void setTransactionAmount(int amount) {
        this.transactionAmount = amount;
    }

    @Override
    public void setCurrentBalance(int balance) {
        this.currentBalance = balance;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    @Override
    public TransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public Long getTime() {
        return transactionTime;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public int getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public int getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userId='" + userId + '\'' +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                ", transactionTime=" + transactionTime +
                '}';
    }
}
