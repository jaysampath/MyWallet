package com.wallet.MyWallet.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = IAccount.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(columnNames = IAccount.EMAIL)
})
public class Account implements IAccount{

    @Id
    @Column(name = IAccount.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = IAccount.EMAIL)
    private String email;

    @Column(name = IAccount.USERNAME)
    private String username;

    @Column(name = IAccount.PREFERRED_USERNAME)
    private String prettyName;

    @Column(name = IAccount.PASSWORD)
    private String password;

    @Column(name = IAccount.TYPE)
    private String type;

    @Column(name = IAccount.STATUS)
    private String status;

    @Column(name = IAccount.TRANSACTION_PASSWORD)
    private String transactionPassword;

    @Column(name = IAccount.BALANCE)
    private int balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPreferredUsername(String prettyName) {
       this.prettyName = prettyName.length()==0 ? username : prettyName;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

    @Override
    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPreferredUsername() {
        return prettyName == null || prettyName.length()==0 ? username : prettyName;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getTransactionPassword() {
        return transactionPassword;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "XXXXX" + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", transactionPassword='" + "XXXXX" + '\'' +
                ", balance=" + balance +
                '}';
    }
}
